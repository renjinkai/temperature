package com.skyform.modules.system.service.mapper;

import com.skyform.mapper.EntityMapper;
import com.skyform.modules.system.domain.AppGroupPersonRelation;
import com.skyform.modules.system.service.dto.AppGroupPersonRelationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
* @author renjk
* @date 2020-06-19
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppGroupPersonRelationMapper extends EntityMapper<AppGroupPersonRelationDTO, AppGroupPersonRelation> {

}