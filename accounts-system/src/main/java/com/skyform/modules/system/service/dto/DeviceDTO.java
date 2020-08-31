package com.skyform.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author renjk
* @date 2020-06-01
*/
@Data
public class DeviceDTO implements Serializable {

    // 主键ID
    private Integer id;

    // 设备ID
    private String deviceId;

    // 入库时间
    private Timestamp createTime;

    // 绑定时间
    private Timestamp bindTime;

    // 姓名
    private String name;

    // 身份证号
    private String idCard;

    // 状态
    private String status;

    private DeptDTO deptDTO;

    // 电话
    private String phone;

    private Double temperature;
}
