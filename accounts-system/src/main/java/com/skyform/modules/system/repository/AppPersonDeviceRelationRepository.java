package com.skyform.modules.system.repository;

import com.skyform.modules.system.domain.AppPersonDeviceRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author renjk
* @date 2020-06-19
*/
public interface AppPersonDeviceRelationRepository extends JpaRepository<AppPersonDeviceRelation, Long>, JpaSpecificationExecutor {
}