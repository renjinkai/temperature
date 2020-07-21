package com.skyform.modules.system.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author renjk
* @date 2020-07-16
*/
@Entity
@Data
@Table(name="device_message")
public class DeviceMessage implements Serializable {

    // 主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 消息类型
    @Column(name = "message_type")
    private String messageType;

    // 产品ID
    @Column(name = "product_id")
    private String productId;

    // 设备ID
    @Column(name = "device_id")
    private String deviceId;

    // 用户ID
    @Column(name = "tenant_id")
    private String tenantId;

    // 设备内容
    @Column(name = "pay_load")
    private String payLoad;

    // 消息体
    @Column(name = "message")
    private String message;

    // 设备时间
    @Column(name = "device_time")
    private Timestamp deviceTime;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

    public void copy(DeviceMessage source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}