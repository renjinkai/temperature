package com.skyform.modules.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skyform.modules.system.domain.Job;
import org.springframework.data.jpa.repository.Query;

public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor {

    @Query(value = "SELECT * FROM job WHERE `name` = ?1",nativeQuery = true)
    Job findJobByName(String name);
}