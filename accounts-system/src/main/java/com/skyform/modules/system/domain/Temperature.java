package com.skyform.modules.system.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author renjk
* @date 2020-05-29
*/
@Entity
@Data
@Table(name="temperature")
public class Temperature implements Serializable {

    // 主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 姓名
    @Column(name = "name")
    private String name;

    // 身份证号
    @Column(name = "id_card")
    private String idCard;

    // 设备ID
    @Column(name = "device_id")
    private String deviceId;

    // 温度
    @Column(name = "temperature")
    private Double temperature;

    // 记录时间
    @Column(name = "record_time")
    private Timestamp recordTime;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

    // 手机号
    @Column(name = "phone")
    private String phone;

    @OneToOne
    @JoinColumn(name = "dept_id")
    private Dept dept;

    public void copy(Temperature source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
