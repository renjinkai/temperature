package com.skyform.modules.system.repository;

import com.skyform.modules.system.domain.DeviceMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author renjk
* @date 2020-07-16
*/
public interface DeviceMessageRepository extends JpaRepository<DeviceMessage, Long>, JpaSpecificationExecutor {
}