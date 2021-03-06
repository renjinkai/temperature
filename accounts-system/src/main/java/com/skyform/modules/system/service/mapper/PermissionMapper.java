package com.skyform.modules.system.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.skyform.mapper.EntityMapper;
import com.skyform.modules.system.domain.Permission;
import com.skyform.modules.system.service.dto.PermissionDTO;
import org.springframework.stereotype.Repository;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Repository
public interface PermissionMapper extends EntityMapper<PermissionDTO, Permission> {

}
