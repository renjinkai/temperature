package com.skyform.modules.system.service.mapper;

import com.skyform.mapper.EntityMapper;
import com.skyform.modules.system.domain.MessageLog;
import com.skyform.modules.system.service.dto.MessageLogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Repository;

/**
* @author renjk
* @date 2020-06-28
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Repository
public interface MessageLogMapper extends EntityMapper<MessageLogDTO, MessageLog> {

}
