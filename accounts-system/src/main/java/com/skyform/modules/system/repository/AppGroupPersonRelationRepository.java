package com.skyform.modules.system.repository;

import com.skyform.modules.system.domain.AppGroupPersonRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author renjk
* @date 2020-06-19
*/
public interface AppGroupPersonRelationRepository extends JpaRepository<AppGroupPersonRelation, Long>, JpaSpecificationExecutor {
}