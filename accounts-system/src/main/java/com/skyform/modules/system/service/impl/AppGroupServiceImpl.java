package com.skyform.modules.system.service.impl;

import com.skyform.exception.BadRequestException;
import com.skyform.modules.system.domain.AppGroup;
import com.skyform.modules.system.domain.AppGroupPersonRelation;
import com.skyform.modules.system.repository.AppGroupRepository;
import com.skyform.modules.system.service.AppGroupPersonRelationService;
import com.skyform.modules.system.service.AppGroupService;
import com.skyform.modules.system.service.UserService;
import com.skyform.modules.system.service.dto.*;
import com.skyform.modules.system.service.mapper.AppGroupMapper;
import com.skyform.modules.system.service.mybatis_mapper.AppGroupMybatisMapper;
import com.skyform.utils.QueryHelp;
import com.skyform.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* @author renjk
* @date 2020-06-19
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AppGroupServiceImpl implements AppGroupService {

    @Autowired
    private AppGroupRepository appGroupRepository;

    @Autowired
    private AppGroupMapper appGroupMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private AppGroupPersonRelationService appGroupPersonRelationService;

    @Autowired
    private AppGroupMybatisMapper appGroupMybatisMapper;

    @Override
    public Object queryAll(AppGroupQueryCriteria criteria, Pageable pageable){
        Map<String, Object> map = new HashMap<>();
        String phone = criteria.getContact();
        if(!"".equals(phone)&&phone!=null){
            UserDTO user = userService.findByName(phone);
            if(user == null){
                map.put("message", "用户" + phone + "不存在");
                map.put("data", "");
                map.put("code", "-1");
                map.put("time", new Date().toString());
                return map;
            }
            List<AppGroup> list = appGroupRepository.getGroupByUser(phone);
            map.put("message", "");
            map.put("data", list);
            map.put("code", "0");
            map.put("time", new Date().toString());
            return map;
        } else{
            int page = 0;
            int size = 10;
            if(pageable.getPageNumber() !=0 && pageable.getPageSize() != 0){
                page = (pageable.getPageNumber()-1)*pageable.getPageSize();
                size = pageable.getPageSize();
            }
            long userId = criteria.getUserId();
            UserDTO userDTO = userService.findById(userId);
            List<RoleSmallDTO> list = new ArrayList(userDTO.getRoles());
            for(RoleSmallDTO roleSmallDTO : list){
                if("超级管理员".equals(roleSmallDTO.getName())){
                    userId = 0;
                    criteria.setUserId(userId);
                }
            }
            Pager<AppGroup> pager = new Pager<>();
            criteria.setPage(page);
            criteria.setSize(size);
            List<AppGroup> appGroupList = appGroupMybatisMapper.query(criteria);
            pager.setContent(appGroupList);
            pager.setTotalElements(appGroupMybatisMapper.count(criteria));
            return pager;
        }
    }

    @Override
    public List<AppGroupDTO> queryAll(AppGroupQueryCriteria criteria){
        return appGroupMapper.toDto(appGroupRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public AppGroupDTO findById(Long id) {
        Optional<AppGroup> appGroup = appGroupRepository.findById(id);
        ValidationUtil.isNull(appGroup,"AppGroup","id",id);
        return appGroupMapper.toDto(appGroup.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AppGroupDTO create(AppGroup resources) {
        // 校验群组编码唯一性
        AppGroup appGroup = this.findByCode(resources.getCode());
        if(appGroup!=null){
            throw new BadRequestException("群组编码已存在，请更换一个编码");
        }else{
            AppGroupDTO appGroupDTO = appGroupMapper.toDto(appGroupRepository.save(resources));
            UserDTO user = userService.findByName(resources.getCreateBy());
            AppGroupPersonRelation appGroupPersonRelation = new AppGroupPersonRelation();
            appGroupPersonRelation.setGroupId(appGroupDTO.getId());
            appGroupPersonRelation.setUserId(user.getId());
            appGroupPersonRelationService.create(appGroupPersonRelation);
            return appGroupDTO;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(AppGroup resources) {
        Optional<AppGroup> optionalAppGroup = appGroupRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalAppGroup,"AppGroup","id",resources.getId());
        AppGroup appGroup = optionalAppGroup.get();
        appGroup.copy(resources);
        appGroupRepository.save(appGroup);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        appGroupRepository.deleteById(id);
    }

    @Override
    public AppGroup findByCode(String code) {
        return appGroupRepository.findByCode(code);
    }

    @Override
    public Map<String, String> inAppGroup(String code, long userId) {
        Map<String, String> map = new HashMap<String, String>();
        AppGroup appGroup = appGroupRepository.findByCode(code);
        if(appGroup == null){
            map.put("data", "");
            map.put("message", "群组编码"+code+"不存在");
            map.put("code", "-1");
            map.put("time", new Date().toString());
            return map;
        }
        AppGroupPersonRelation appGroupPersonRelation = new AppGroupPersonRelation();
        appGroupPersonRelation.setGroupId(appGroup.getId());
        appGroupPersonRelation.setUserId(userId);
        appGroupPersonRelationService.create(appGroupPersonRelation);
        map.put("data", "");
        map.put("message", "加入群组成功");
        map.put("code", "0");
        map.put("time", new Date().toString());
        return map;
    }

    @Override
    public Map<String, String> outAppGroup(String code, long userId) {
        Map<String, String> map = new HashMap<String, String>();
        AppGroup appGroup = appGroupRepository.findByCode(code);
        if(appGroup == null){
            map.put("data", "");
            map.put("message", "群组编码"+code+"不存在");
            map.put("code", "-1");
            map.put("time", new Date().toString());
            return map;
        }
        AppGroupPersonRelationQueryCriteria criteria = new AppGroupPersonRelationQueryCriteria();
        criteria.setGroupId(appGroup.getId());
        criteria.setUserId(userId);
        List<AppGroupPersonRelationDTO> list = appGroupPersonRelationService.queryAll(criteria);
        if(list.size()==0){
            map.put("data", "");
            map.put("message", "用户不在此群组中");
            map.put("code", "-1");
            map.put("time", new Date().toString());
            return map;
        }else{
            appGroupPersonRelationService.delete(list.get(0).getId());
            map.put("data", "");
            map.put("message", "退出群组成功");
            map.put("code", "0");
            map.put("time", new Date().toString());
            return map;
        }
    }

    @Override
    public Map<String, Object> appGroupCount(long groupId) {
        Map<String, Object> map = new HashMap<>();
        int countUsers = appGroupRepository.countUsers(groupId);
        int countDevices = appGroupRepository.countDevices(groupId);
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("countUsers", countUsers);
        resultMap.put("countDevices", countDevices);
        map.put("data", resultMap);
        map.put("code", "0");
        map.put("time", new Date().toString());
        return map;
    }
}
