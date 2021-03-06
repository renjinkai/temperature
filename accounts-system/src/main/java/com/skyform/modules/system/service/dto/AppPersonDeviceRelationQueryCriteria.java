package com.skyform.modules.system.service.dto;

import lombok.Data;
import com.skyform.annotation.Query;

/**
* @author renjk
* @date 2020-06-19
*/
@Data
public class AppPersonDeviceRelationQueryCriteria{

    // 精确
    @Query
    private Long id;

    // 精确
    @Query
    private Long userId;

    // 精确
    @Query
    private Long deviceId;
}