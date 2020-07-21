package com.skyform.service.dto;

import com.skyform.annotation.Query;

import lombok.Data;

/**
 * 日志查询类
 */
@Data
public class LogQueryCriteria {

    @Query(type = Query.Type.INNER_LIKE)
    private String username;

    @Query
    private String logType;

    @Query(type = Query.Type.INNER_LIKE)
    private String description;
}
