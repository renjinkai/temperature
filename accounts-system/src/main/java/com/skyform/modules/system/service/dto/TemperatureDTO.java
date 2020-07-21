package com.skyform.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author renjk
* @date 2020-05-29
*/
@Data
public class TemperatureDTO implements Serializable {

    // 主键ID
    private Long id;

    // 姓名
    private String name;

    // 身份证号
    private String idCard;

    // 设备ID
    private String deviceId;

    // 温度
    private Double temperature;

    // 记录时间
    private Timestamp recordTime;

    // 创建时间
    private Timestamp createTime;

    // 手机号
    private String phone;
}