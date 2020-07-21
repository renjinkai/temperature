package com.skyform.modules.system.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author renjk
* @date 2020-06-19
*/
@Entity
@Data
@Table(name="app_person_device_relation")
public class AppPersonDeviceRelation implements Serializable {

    // 主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 监控人ID
    @Column(name = "user_id")
    private Long userId;

    // 设备ID
    @Column(name = "device_id")
    private Long deviceId;

    public void copy(AppPersonDeviceRelation source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}