package com.skyform.modules.system.repository;

import com.skyform.modules.system.domain.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
* @author renjk
* @date 2020-05-29
*/
public interface TemperatureRepository extends JpaRepository<Temperature, Long>, JpaSpecificationExecutor {

    @Query(value = "DELETE FROM temperature WHERE device_id = ?1",nativeQuery = true)
    void deleteByDeviceId(String deviceId);
}