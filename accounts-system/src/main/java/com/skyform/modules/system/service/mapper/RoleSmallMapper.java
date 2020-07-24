package com.skyform.modules.system.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.skyform.mapper.EntityMapper;
import com.skyform.modules.system.domain.Role;
import com.skyform.modules.system.service.dto.RoleDTO;
import com.skyform.modules.system.service.dto.RoleSmallDTO;
import org.springframework.stereotype.Repository;

@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Repository
public interface RoleSmallMapper extends EntityMapper<RoleSmallDTO, Role> {

}
