package com.skyform.modules.system.service.mybatis_mapper;

import com.skyform.modules.system.domain.AppGroup;
import com.skyform.modules.system.service.dto.AppGroupQueryCriteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppGroupMybatisMapper {

    List<AppGroup> query(@Param("criteria") AppGroupQueryCriteria criteria);

}
