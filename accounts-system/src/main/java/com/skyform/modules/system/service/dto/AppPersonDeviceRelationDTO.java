package com.skyform.modules.system.service.dto;

import lombok.Data;
import java.io.Serializable;


/**
* @author renjk
* @date 2020-06-19
*/
@Data
public class AppPersonDeviceRelationDTO implements Serializable {

    // 主键ID
    private Long id;

    // 监控人ID
    private Long userId;

    // 设备ID
    private Long deviceId;
}