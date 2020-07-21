package com.skyform.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import com.skyform.annotation.Query;

/**
* @author renjk
* @date 2020-07-16
*/
@Data
public class DeviceMessageQueryCriteria{

    // 精确
    @Query
    private Long id;

    // 精确
    @Query
    private String messageType;

    // 精确
    @Query
    private String productId;

    // 精确
    @Query
    private String deviceId;

    // 精确
    @Query
    private String tenantId;
}