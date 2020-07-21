package com.skyform.modules.system.service.mybatis_mapper;

import com.skyform.modules.system.service.dto.TemperatureDTO;
import com.skyform.modules.system.service.dto.TemperatureQueryCriteria;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemperatureMybatisMapper {

    List<TemperatureDTO> query(@Param("criteria") TemperatureQueryCriteria criteria);

}
