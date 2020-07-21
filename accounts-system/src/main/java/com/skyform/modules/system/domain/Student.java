package com.skyform.modules.system.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
* @author renjk
* @date 2020-05-29
*/
@Entity
@Data
@Table(name="student")
public class Student implements Serializable {

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

    @OneToOne
    @JoinColumn(name = "dept_id")
    private Dept dept;

    // 学校ID
    @Column(name = "dept_school_id")
    private Long deptSchoolId;

    // 学校
    @Column(name = "dept_school")
    private String deptSchool;

    // 家长姓名
    @Column(name = "parent")
    private String parent;

    // 家长联系方式
    @Column(name = "parent_phone")
    private String parentPhone;

    // 住校地址
    @Column(name = "address")
    private String address;

    // 房间号
    @Column(name = "room")
    private String room;

    // 责任人
    @Column(name = "alarm_person")
    private String alarmPerson;

    // 责任人手机号
    @Column(name = "alarm_phone")
    private String alarmPhone;

    // 责任人微信号
    @Column(name = "alarm_wechat")
    private String alarmWechat;

    // 通知方式
    @Column(name = "alarm_method")
    private String alarmMethod;

    // 最近一次检测体温
    @Column(name = "recent_temperature")
    private Double recentTemperature;

    // 最近一次检测时间
    @Column(name = "recent_time")
    private Timestamp recentTime;

    // 轨迹
    @Column(name = "loca")
    private String loca;

    // 绑定状态
    @Column(name = "bind_status")
    private String bindStatus = "false";

    public void copy(Student source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
