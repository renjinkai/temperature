package com.skyform.modules.system.service.dto;

import com.skyform.annotation.Query;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

/**
* @author renjk
* @date 2020-05-29
*/
@Data
public class TemperatureQueryCriteria{

    // 精确
    @Query
    private Long id;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String idCard;

    // 精确
    @Query
    private String deviceId;

    // 精确
    @Query
    private Double temperature;

    private Timestamp recordTime;

    private String recordStartTime;

    private String recordEndTime;

    @Query
    private String phone;

    private int page = 0;

    private int size = 100;

    @Query(propName = "id", joinName = "dept")
    private Long deptId;

    @Query(propName = "id", joinName = "dept", type = Query.Type.IN)
    private Set<Long> deptIds;

}
