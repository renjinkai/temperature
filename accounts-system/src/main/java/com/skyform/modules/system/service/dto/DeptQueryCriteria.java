package com.skyform.modules.system.service.dto;

import lombok.Data;

import java.util.Set;

import com.skyform.annotation.Query;

@Data
public class DeptQueryCriteria{

    @Query
    private Long id;

    @Query(type = Query.Type.IN, propName="id")
    private Set<Long> ids;

    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    @Query
    private Boolean enabled;

    @Query
    private Long pid;

    @Query
    private String type;

    @Query
    private String code;

    @Query
    private String level;
}
