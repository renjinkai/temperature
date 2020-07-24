package com.skyform.modules.system.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.skyform.mapper.EntityMapper;
import com.skyform.modules.system.domain.Dept;
import com.skyform.modules.system.service.dto.DeptDTO;
import org.springframework.stereotype.Repository;

@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Repository
public interface DeptMapper extends EntityMapper<DeptDTO, Dept> {

}
