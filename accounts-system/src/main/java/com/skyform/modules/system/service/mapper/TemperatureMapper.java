package com.skyform.modules.system.service.mapper;

import com.skyform.mapper.EntityMapper;
import com.skyform.modules.system.domain.Temperature;
import com.skyform.modules.system.service.dto.TemperatureDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author renjk
* @date 2020-05-29
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TemperatureMapper extends EntityMapper<TemperatureDTO, Temperature> {

}