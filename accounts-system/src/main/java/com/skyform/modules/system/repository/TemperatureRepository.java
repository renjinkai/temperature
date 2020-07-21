package com.skyform.modules.system.repository;

import com.skyform.modules.system.domain.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
* @author renjk
* @date 2020-05-29
*/
public interface TemperatureRepository extends JpaRepository<Temperature, Long>, JpaSpecificationExecutor {

    @Query(value = "DELETE FROM temperature WHERE device_id = ?1",nativeQuery = true)
    void deleteByDeviceId(String deviceId);

    @Query(value = "SELECT device_id FROM temperature WHERE dept_id IN ?1 GROUP BY device_id",nativeQuery = true)
    List<String> findDeviceIdByDeptIds(List<Long> subDeptIds);

    @Query(value = "SELECT * FROM temperature WHERE device_id = ?1 ORDER BY record_time DESC LIMIT 3",nativeQuery = true)
    List<Temperature> find3RecordList(String deviceId);
}
