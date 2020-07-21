package com.skyform.modules.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.skyform.modules.system.domain.Device;
import com.skyform.modules.system.domain.Student;
import com.skyform.modules.system.repository.DeviceRepository;
import com.skyform.modules.system.service.DeviceService;
import com.skyform.modules.system.service.StudentService;
import com.skyform.modules.system.service.dto.DeviceDTO;
import com.skyform.modules.system.service.dto.DeviceQueryCriteria;
import com.skyform.modules.system.service.mapper.DeviceMapper;
import com.skyform.modules.system.service.mybatis_mapper.DeviceMybatisMapper;
import com.skyform.utils.PageUtil;
import com.skyform.utils.QueryHelp;
import com.skyform.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author renjk
 * @date 2020-06-01
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private DeviceMybatisMapper deviceMybatisMapper;

    @Autowired
    private StudentService studentService;

    @Override
    public Object queryAll(DeviceQueryCriteria criteria, Pageable pageable) {
        Page<Device> page = deviceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(deviceMapper::toDto));
    }

    @Override
    public List<DeviceDTO> queryAll(DeviceQueryCriteria criteria) {
        return deviceMapper.toDto(deviceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder)));
    }

    @Override
    public DeviceDTO findById(Long id) {
        Optional<Device> device = deviceRepository.findById(id);
        ValidationUtil.isNull(device, "Device", "id", id);
        return deviceMapper.toDto(device.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeviceDTO create(Device resources) {
        resources.setCreateTime(new Timestamp(new Date().getTime()));
        if(resources.getStatus() != null && !"".equals(resources.getStatus())){
            resources.setStatus(resources.getStatus());
        }else{
            resources.setStatus("false");
        }
        return deviceMapper.toDto(deviceRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Device resources) {
        Optional<Device> optionalDevice = deviceRepository.findById(resources.getId());
        ValidationUtil.isNull(optionalDevice, "Device", "id", resources.getId());
        Device device = optionalDevice.get();
        device.copy(resources);
        deviceRepository.save(device);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        deviceRepository.deleteById(id);
    }

    @Override
    public void updateByDeviceId(Device device) {
        deviceMybatisMapper.updateByDeviceId(device);
    }

    @Override
    public void setNull(Device device) {
        deviceMybatisMapper.setNull(device);
    }

    @Override
    public void bind(String deviceId, String idCard, String name) {
        // 更新设备表 device
        Device device = new Device();
        device.setDeviceId(deviceId);
        device.setIdCard(idCard);
        device.setName(name);
        device.setBindTime(new Timestamp(new Date().getTime()));
        device.setStatus("true");
        this.updateByDeviceId(device);
        // 更新学生表 student
        Student student = new Student();
        student.setIdCard(idCard);
        student.setDeviceId(deviceId);
        student.setBindStatus("true");
        studentService.updateByIdCard(student);
    }

    @Override
    public void unbind(String deviceId, String idCard) {
        // 更新设备表 device
        Device device = new Device();
        device.setDeviceId(deviceId);
        this.setNull(device);
        // 更新学生表 student
        Student student = new Student();
        student.setIdCard(idCard);
        student.setDeviceId(null);
        student.setBindStatus("false");
        studentService.updateByIdCard(student);
    }

    @Override
    public Device findByDeviceId(String deviceId) {
        return deviceRepository.findByDeviceId(deviceId);
    }

    @Override
    public void updateNameByDeviceId(String name, String deviceId) {
        deviceRepository.updateNameByDeviceId(name, deviceId);
    }

    @Override
    public List<Device> getDeviceByUserId(long userId) {
        return deviceRepository.getDeviceByUserId(userId);
    }
}
