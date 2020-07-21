package com.skyform.modules.system.service.mapper;

import com.skyform.mapper.EntityMapper;
import com.skyform.modules.system.domain.DeviceMessage;
import com.skyform.modules.system.service.dto.DeviceMessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author renjk
* @date 2020-07-16
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeviceMessageMapper extends EntityMapper<DeviceMessageDTO, DeviceMessage> {

}