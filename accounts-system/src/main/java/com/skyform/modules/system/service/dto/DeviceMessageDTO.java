package com.skyform.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author renjk
* @date 2020-07-16
*/
@Data
public class DeviceMessageDTO implements Serializable {

    // 主键ID
    private Long id;

    // 消息类型
    private String messageType;

    // 产品ID
    private String productId;

    // 设备ID
    private String deviceId;

    // 用户ID
    private String tenantId;

    // 设备内容
    private String payLoad;

    // 消息体
    private String message;

    // 设备时间
    private Timestamp deviceTime;

    // 创建时间
    private Timestamp createTime;
}