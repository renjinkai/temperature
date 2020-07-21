package com.skyform.modules.system.service.dto;

import lombok.Data;
import com.skyform.annotation.Query;

/**
* @author renjk
* @date 2020-06-19
*/
@Data
public class AppGroupQueryCriteria{

    // 精确
    @Query
    private Long id;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    // 精确
    @Query
    private String code;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String createBy;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String contact;
}