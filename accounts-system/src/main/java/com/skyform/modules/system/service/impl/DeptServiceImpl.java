package com.skyform.modules.system.service.impl;

import com.skyform.modules.system.domain.User;
import com.skyform.modules.system.service.UserService;
import com.skyform.utils.PageUtil;
import com.skyform.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.skyform.exception.BadRequestException;
import com.skyform.modules.system.domain.Dept;
import com.skyform.modules.system.repository.DeptRepository;
import com.skyform.modules.system.service.DeptService;
import com.skyform.modules.system.service.dto.DeptDTO;
import com.skyform.modules.system.service.dto.DeptQueryCriteria;
import com.skyform.modules.system.service.mapper.DeptMapper;
import com.skyform.utils.QueryHelp;
import com.skyform.utils.ValidationUtil;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public Object queryAll(DeptQueryCriteria criteria, Pageable pageable) {
        Page<Dept> page = deptRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(deptMapper::toDto));
    }

    @Override
    public List<DeptDTO> queryAll(DeptQueryCriteria criteria) {
        return deptMapper.toDto(deptRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public DeptDTO findById(Long id) {
        Optional<Dept> dept = deptRepository.findById(id);
        ValidationUtil.isNull(dept,"Dept","id",id);
        return deptMapper.toDto(dept.get());
    }

    @Override
    public List<Dept> findByPid(long pid) {
        return deptRepository.findByPid(pid);
    }

    @Override
    public Page<Dept> findSubDeptById(Long deptId, Pageable pageable) {
        return deptRepository.findSubDeptById(deptId, pageable);
    }

    @Override
    public Set<Dept> findByRoleIds(Long id) {
        return deptRepository.findByRoles_Id(id);
    }

    @Override
    public void bindByCode(Long deptId, List<String> codeList) {
        DeptDTO deptDTO1 = this.findById(deptId);
        if(deptDTO1 != null){
            // 当前登录用户所在机构部门ID，此ID是需要通过验证码关联的子机构部门的父pid
            long pid = deptId;
            // 通过验证码查询机构部门，并更新pid
            for(String code : codeList){
                DeptQueryCriteria criteria2 = new DeptQueryCriteria();
                criteria2.setCode(code);
                List<DeptDTO> deptDTOS2 = this.queryAll(criteria2);
                if(deptDTOS2.size() > 0){
                    DeptDTO deptDTO = deptDTOS2.get(0);
                    Dept dept = new Dept();
                    dept.setId(deptDTO.getId());
                    dept.setName(deptDTO.getName());
                    dept.setPid(pid);
                    dept.setCreateTime(deptDTO.getCreateTime());
                    dept.setEnabled(deptDTO.getEnabled());
                    dept.setType(deptDTO.getType());
                    dept.setCode(deptDTO.getCode());
                    dept.setFrequency(deptDTO.getFrequency());
                    dept.setOverFrequency(deptDTO.getOverFrequency());
                    dept.setOverTime(deptDTO.getOverTime());
                    this.update(dept);
                }else{
                    throw new BadRequestException("验证码未能查询到机构部门");
                }
            }
        }else{
            throw new BadRequestException("未查询到当前机构部门");
        }
    }

    @Override
    public Object buildTree(List<DeptDTO> deptDTOS) {
        Set<DeptDTO> trees = new LinkedHashSet<>();
        Set<DeptDTO> depts= new LinkedHashSet<>();
        List<String> deptNames = deptDTOS.stream().map(DeptDTO::getName).collect(Collectors.toList());
        Boolean isChild;
        for (DeptDTO deptDTO : deptDTOS) {
            isChild = false;
            if ("0".equals(deptDTO.getPid().toString())) {
                trees.add(deptDTO);
            }
            for (DeptDTO it : deptDTOS) {
                if (it.getPid().equals(deptDTO.getId())) {
                    isChild = true;
                    if (deptDTO.getChildren() == null) {
                        deptDTO.setChildren(new ArrayList<DeptDTO>());
                    }
                    deptDTO.getChildren().add(it);
                }
            }
            if(isChild)
                depts.add(deptDTO);
            else if(!deptNames.contains(deptRepository.findNameById(deptDTO.getPid())))
                depts.add(deptDTO);
        }

        if (CollectionUtils.isEmpty(trees)) {
            trees = depts;
        }

        Integer totalElements = deptDTOS!=null?deptDTOS.size():0;

        Map map = new HashMap();
        map.put("totalElements",totalElements);
        map.put("content",CollectionUtils.isEmpty(trees)?deptDTOS:trees);
        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeptDTO create(Dept resources) {
        String code;
        List<DeptDTO> list;
        DeptQueryCriteria criteria = new DeptQueryCriteria();
        do {
            code = UuidUtil.genUUID();
            criteria.setCode(code);
            list = this.queryAll(criteria);
        }while (list.size() > 0);
        resources.setCode(code);
        resources.setFrequency(60);
        resources.setOverFrequency(15);
        resources.setOverTime(3);
        return deptMapper.toDto(deptRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Dept resources) {
        if(resources.getId().equals(resources.getPid())) {
            throw new BadRequestException("上级不能为自己");
        }
        Optional<Dept> optionalDept = deptRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalDept,"Dept","id",resources.getId());
        Dept dept = optionalDept.get();
        resources.setId(dept.getId());
        if(resources.getName() == null || "".equals(resources.getName())){
            resources.setName(dept.getName());
        }
        resources.setEnabled(dept.getEnabled());
        if(resources.getPid() == null){
            resources.setPid(dept.getPid());
        }
        resources.setCreateTime(dept.getCreateTime());
        if(resources.getType() == null || "".equals(resources.getType())){
            resources.setType(dept.getType());
        }
        resources.setCode(dept.getCode());
        if(resources.getFrequency() == null){
            resources.setFrequency(dept.getFrequency());
        }
        if(resources.getFrequency() == null){
            resources.setOverTime(dept.getOverTime());
        }
        if(resources.getFrequency() == null){
            resources.setOverFrequency(dept.getOverFrequency());
        }
        deptRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        // 判断部门下是否还有用户,如果有，则不能删除部门
        List<User> list = userService.findByDeptId(id);
        if(list.size() == 0){
            deptRepository.deleteById(id);
        }else{
            throw new BadRequestException("此部门下有未删除的用户，不能删除此部门");
        }
    }
}