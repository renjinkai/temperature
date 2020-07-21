package com.skyform.modules.quartz.service.dto;

import com.skyform.annotation.Query;

import lombok.Data;

@Data
public class JobQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String jobName;

    @Query
    private Boolean isSuccess;
}
