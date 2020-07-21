package com.skyform.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.Set;

import com.skyform.annotation.Query;

/**
* @author renjk
* @date 2020-06-01
*/
@Data
public class DeviceQueryCriteria{

    // 精确
    @Query
    private Integer id;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String deviceId;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String idCard;

    // 精确
    @Query
    private String status;

    @Query(propName = "id", joinName = "dept")
    private Long deptId;

    @Query(propName = "id", joinName = "dept", type = Query.Type.IN)
    private Set<Long> deptIds;
}
