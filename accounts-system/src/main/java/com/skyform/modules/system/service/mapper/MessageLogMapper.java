package com.skyform.modules.system.service.mapper;

import com.skyform.mapper.EntityMapper;
import com.skyform.modules.system.domain.MessageLog;
import com.skyform.modules.system.service.dto.MessageLogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author renjk
* @date 2020-06-28
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageLogMapper extends EntityMapper<MessageLogDTO, MessageLog> {

}