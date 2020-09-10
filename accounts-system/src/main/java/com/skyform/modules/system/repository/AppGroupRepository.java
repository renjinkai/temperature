package com.skyform.modules.system.repository;

import com.skyform.modules.system.domain.AppGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
* @author renjk
* @date 2020-06-19
*/
public interface AppGroupRepository extends JpaRepository<AppGroup, Long>, JpaSpecificationExecutor {

    @Query(value = "SELECT * FROM app_group WHERE `code` = ?1",nativeQuery = true)
    AppGroup findByCode(String code);

    @Query(value = "SELECT COUNT(1) FROM app_group_person_relation WHERE group_id = ?1",nativeQuery = true)
    int countUsers(long groupId);

    @Query(value = "SELECT COUNT(1) FROM app_person_device_relation WHERE user_id IN (SELECT user_id FROM app_group_person_relation WHERE group_id = ?1)",nativeQuery = true)
    int countDevices(long groupId);

    @Query(value = "SELECT * FROM app_group WHERE id IN (SELECT group_id FROM app_group_person_relation WHERE user_id = (SELECT id FROM `user` WHERE username = ?1))",nativeQuery = true)
    List<AppGroup> getGroupByUser(String phone);
}
