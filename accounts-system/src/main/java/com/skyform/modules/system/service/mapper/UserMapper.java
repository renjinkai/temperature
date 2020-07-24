package com.skyform.modules.system.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.skyform.mapper.EntityMapper;
import com.skyform.modules.system.domain.User;
import com.skyform.modules.system.service.dto.UserDTO;
import org.springframework.stereotype.Repository;

@Mapper(componentModel = "spring",uses = {RoleMapper.class, DeptMapper.class, JobMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Repository
public interface UserMapper extends EntityMapper<UserDTO, User> {

}
