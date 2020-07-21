package com.skyform.modules.system.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author renjk
* @date 2020-06-01
*/
@Entity
@Data
@Table(name="device")
public class Device implements Serializable {

    // 主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 设备ID
    @Column(name = "device_id")
    private String deviceId;

    // 入库时间
    @Column(name = "create_time")
    private Timestamp createTime;

    // 绑定时间
    @Column(name = "bind_time")
    private Timestamp bindTime;

    // 姓名
    @Column(name = "name")
    private String name;

    // 身份证号
    @Column(name = "id_card")
    private String idCard;

    // 状态
    @Column(name = "status")
    private String status;

    public void copy(Device source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}