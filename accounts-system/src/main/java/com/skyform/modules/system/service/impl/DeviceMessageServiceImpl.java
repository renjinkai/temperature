package com.skyform.modules.system.service.impl;

import com.skyform.modules.system.domain.DeviceMessage;
import com.skyform.utils.ValidationUtil;
import com.skyform.modules.system.repository.DeviceMessageRepository;
import com.skyform.modules.system.service.DeviceMessageService;
import com.skyform.modules.system.service.dto.DeviceMessageDTO;
import com.skyform.modules.system.service.dto.DeviceMessageQueryCriteria;
import com.skyform.modules.system.service.mapper.DeviceMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.skyform.utils.PageUtil;
import com.skyform.utils.QueryHelp;

/**
* @author renjk
* @date 2020-07-16
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeviceMessageServiceImpl implements DeviceMessageService {

    @Autowired
    private DeviceMessageRepository deviceMessageRepository;

    @Autowired
    private DeviceMessageMapper deviceMessageMapper;

    @Override
    public Object queryAll(DeviceMessageQueryCriteria criteria, Pageable pageable){
        Page<DeviceMessage> page = deviceMessageRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(deviceMessageMapper::toDto));
    }

    @Override
    public Object queryAll(DeviceMessageQueryCriteria criteria){
        return deviceMessageMapper.toDto(deviceMessageRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public DeviceMessageDTO findById(Long id) {
        Optional<DeviceMessage> deviceMessage = deviceMessageRepository.findById(id);
        ValidationUtil.isNull(deviceMessage,"DeviceMessage","id",id);
        return deviceMessageMapper.toDto(deviceMessage.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DeviceMessageDTO create(DeviceMessage resources) {
        return deviceMessageMapper.toDto(deviceMessageRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DeviceMessage resources) {
        Optional<DeviceMessage> optionalDeviceMessage = deviceMessageRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalDeviceMessage,"DeviceMessage","id",resources.getId());
        DeviceMessage deviceMessage = optionalDeviceMessage.get();
        deviceMessage.copy(resources);
        deviceMessageRepository.save(deviceMessage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        deviceMessageRepository.deleteById(id);
    }
}