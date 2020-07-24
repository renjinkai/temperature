package com.skyform.modules.system.service.mapper;

import com.skyform.mapper.EntityMapper;
import com.skyform.modules.system.domain.AppGroup;
import com.skyform.modules.system.service.dto.AppGroupDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Repository;

/**
* @author renjk
* @date 2020-06-19
*/
@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Repository
public interface AppGroupMapper extends EntityMapper<AppGroupDTO, AppGroup> {

}
