package com.skyform.modules.system.service.mapper;

import com.skyform.mapper.EntityMapper;
import com.skyform.modules.system.domain.AppPersonDeviceRelation;
import com.skyform.modules.system.service.dto.AppPersonDeviceRelationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Repository;

/**
* @author renjk
* @date 2020-06-19
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Repository
public interface AppPersonDeviceRelationMapper extends EntityMapper<AppPersonDeviceRelationDTO, AppPersonDeviceRelation> {

}
