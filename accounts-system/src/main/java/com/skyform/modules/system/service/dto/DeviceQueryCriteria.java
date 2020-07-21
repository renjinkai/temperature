package com.skyform.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
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
}