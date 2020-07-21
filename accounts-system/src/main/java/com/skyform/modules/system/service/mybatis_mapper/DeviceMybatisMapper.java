package com.skyform.modules.system.service.mybatis_mapper;

import com.skyform.modules.system.domain.Device;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceMybatisMapper {

    void updateByDeviceId(Device device);

    void setNull(Device device);

}
