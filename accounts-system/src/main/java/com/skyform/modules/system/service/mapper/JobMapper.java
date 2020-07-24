package com.skyform.modules.system.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.skyform.mapper.EntityMapper;
import com.skyform.modules.system.domain.Job;
import com.skyform.modules.system.service.dto.JobDTO;
import org.springframework.stereotype.Repository;

@Mapper(componentModel = "spring",uses = {DeptMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Repository
public interface JobMapper extends EntityMapper<JobDTO, Job> {

    @Mapping(source = "deptSuperiorName", target = "deptSuperiorName")
    JobDTO toDto(Job job, String deptSuperiorName);
}
