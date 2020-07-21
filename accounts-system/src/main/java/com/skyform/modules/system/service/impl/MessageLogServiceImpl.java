package com.skyform.modules.system.service.impl;

import com.skyform.modules.system.domain.MessageLog;
import com.skyform.utils.ValidationUtil;
import com.skyform.modules.system.repository.MessageLogRepository;
import com.skyform.modules.system.service.MessageLogService;
import com.skyform.modules.system.service.dto.MessageLogDTO;
import com.skyform.modules.system.service.dto.MessageLogQueryCriteria;
import com.skyform.modules.system.service.mapper.MessageLogMapper;
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
* @date 2020-06-28
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MessageLogServiceImpl implements MessageLogService {

    @Autowired
    private MessageLogRepository messageLogRepository;

    @Autowired
    private MessageLogMapper messageLogMapper;

    @Override
    public Object queryAll(MessageLogQueryCriteria criteria, Pageable pageable){
        Page<MessageLog> page = messageLogRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(messageLogMapper::toDto));
    }

    @Override
    public Object queryAll(MessageLogQueryCriteria criteria){
        return messageLogMapper.toDto(messageLogRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public MessageLogDTO findById(Long id) {
        Optional<MessageLog> messageLog = messageLogRepository.findById(id);
        ValidationUtil.isNull(messageLog,"MessageLog","id",id);
        return messageLogMapper.toDto(messageLog.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public MessageLogDTO create(MessageLog resources) {
        return messageLogMapper.toDto(messageLogRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(MessageLog resources) {
        Optional<MessageLog> optionalMessageLog = messageLogRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalMessageLog,"MessageLog","id",resources.getId());
        MessageLog messageLog = optionalMessageLog.get();
        messageLog.copy(resources);
        messageLogRepository.save(messageLog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        messageLogRepository.deleteById(id);
    }

    @Override
    public int getCountCurrentDate(String phone) {
        return messageLogRepository.getCountCurrentDate(phone);
    }

    @Override
    public int getCountOneMinute(String phone) {
        return messageLogRepository.getCountOneMinute(phone);
    }
}