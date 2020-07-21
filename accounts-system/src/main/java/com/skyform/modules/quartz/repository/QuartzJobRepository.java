package com.skyform.modules.quartz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.skyform.modules.quartz.domain.QuartzJob;

import java.util.List;

public interface QuartzJobRepository extends JpaRepository<QuartzJob,Long>, JpaSpecificationExecutor {

    /**
     * 查询启用的任务
     * @return
     */
    List<QuartzJob> findByIsPauseIsFalse();
}
