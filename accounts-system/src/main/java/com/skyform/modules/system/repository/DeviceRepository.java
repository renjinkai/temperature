package com.skyform.modules.system.repository;

import com.skyform.modules.system.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author renjk
* @date 2020-06-01
*/
public interface DeviceRepository extends JpaRepository<Device, Long>, JpaSpecificationExecutor {

    @Query(value = "SELECT * FROM device WHERE device_id = ?1",nativeQuery = true)
    Device findByDeviceId(String deviceId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE device SET `name` = ?1,bind_time = NOW() WHERE device_id = ?2",nativeQuery = true)
    void updateNameByDeviceId(String name, String deviceId);

    @Query(value = "SELECT * FROM device WHERE id IN (SELECT device_id FROM app_person_device_relation WHERE user_id = ?1)",nativeQuery = true)
    List<Device> getDeviceByUserId(long userId);
}