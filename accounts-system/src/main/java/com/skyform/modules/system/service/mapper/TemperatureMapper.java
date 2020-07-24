package com.skyform.modules.system.service.mapper;

import com.skyform.mapper.EntityMapper;
import com.skyform.modules.system.domain.Temperature;
import com.skyform.modules.system.service.dto.TemperatureDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Repository;

/**
* @author renjk
* @date 2020-05-29
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Repository
public interface TemperatureMapper extends EntityMapper<TemperatureDTO, Temperature> {

}
