package com.skyform.modules.system.service.impl;

import com.skyform.config.DataScope;
import com.skyform.modules.system.domain.Dept;
import com.skyform.modules.system.domain.Temperature;
import com.skyform.modules.system.repository.TemperatureRepository;
import com.skyform.modules.system.service.DeptService;
import com.skyform.modules.system.service.TemperatureService;
import com.skyform.modules.system.service.UserService;
import com.skyform.modules.system.service.dto.*;
import com.skyform.modules.system.service.mapper.TemperatureMapper;
import com.skyform.modules.system.service.mybatis_mapper.TemperatureMybatisMapper;
import com.skyform.utils.PageUtil;
import com.skyform.utils.QueryHelp;
import com.skyform.utils.SecurityUtils;
import com.skyform.utils.ValidationUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* @author renjk
* @date 2020-05-29
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class TemperatureServiceImpl implements TemperatureService {

    @Autowired
    private TemperatureRepository temperatureRepository;

    @Autowired
    private TemperatureMapper temperatureMapper;

    @Autowired
    private TemperatureMybatisMapper temperatureMybatisMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private DataScope dataScope;

    @Override
    public Object queryAll(TemperatureQueryCriteria criteria, Pageable pageable){
        Page<Temperature> page = temperatureRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(temperatureMapper::toDto));
    }

    @Override
    public List<TemperatureDTO> queryAll(TemperatureQueryCriteria criteria){
        return temperatureMapper.toDto(temperatureRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public TemperatureDTO findById(Long id) {
        Optional<Temperature> temperature = temperatureRepository.findById(id);
        ValidationUtil.isNull(temperature,"Temperature","id",id);
        return temperatureMapper.toDto(temperature.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TemperatureDTO create(Temperature resources) {
        return temperatureMapper.toDto(temperatureRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Temperature resources) {
        Optional<Temperature> optionalTemperature = temperatureRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalTemperature,"Temperature","id",resources.getId());
        Temperature temperature = optionalTemperature.get();
        temperature.copy(resources);
        temperatureRepository.save(temperature);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        temperatureRepository.deleteById(id);
    }

    @Override
    public List<TemperatureDTO> query(@Param("criteria") TemperatureQueryCriteria criteria) {
        return temperatureMybatisMapper.query(criteria);
    }

    @Override
    public void deleteByDeviceId(String deviceId) {
        temperatureRepository.deleteByDeviceId(deviceId);
    }

    @Override
    public List<AbnormalDTO> countAbnormal() {
        List<AbnormalDTO> abnormalDTOList = new ArrayList<>();
        // 查询当前登录用户下的一级子部门
        String username = SecurityUtils.getUsername();
        long deptId = userService.findDeptIdByUsername(username);
        List<Dept> subDeptList = deptService.findSubDeptById(deptId);
        if(subDeptList.size()==0){
            // 无子部门，为班级最小单位部门
            DeptQueryCriteria criteria = new DeptQueryCriteria();
            criteria.setId(deptId);
            List<Dept> list = deptService.query(criteria);
            subDeptList = new ArrayList<>();
            subDeptList.add(list.get(0));
        }
        for(Dept subDept:subDeptList){
            // 统计子部门下体温异常人员数量
            List<Dept> deptList = new ArrayList<>();
            deptList.add(subDept);
            List<Long> subDeptIds = dataScope.getDeptChildren(deptList);
            // 查询子部门下所有设备
            List<String> deviceIdList = temperatureRepository.findDeviceIdByDeptIds(subDeptIds);
            int count = 0; // 计数器
            if(deviceIdList.size()>0){
                for(String deviceId:deviceIdList){
                    List<Temperature> temperatureList = temperatureRepository.find3RecordList(deviceId);
                    if(temperatureList.size() == 3){
                        double first = temperatureList.get(0).getTemperature();
                        double second = temperatureList.get(1).getTemperature();
                        double third = temperatureList.get(2).getTemperature();
                        if(first>=37.2 && second>=37.2 && third>=37.2){
                            count++;
                        }
                    }
                }
            }
            AbnormalDTO abnormalDTO = new AbnormalDTO();
            abnormalDTO.setDept(subDept);
            abnormalDTO.setCount(count);
            abnormalDTOList.add(abnormalDTO);
        }
        return abnormalDTOList;
    }
}
