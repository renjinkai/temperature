package com.skyform.modules.system.service.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import lombok.Data;
import java.io.Serializable;
import java.sql.Timestamp;


/**
* @author renjk
* @date 2020-05-29
*/
@Data
public class StudentDTO implements Serializable {

    // 主键ID
    private Long id;

    // 姓名
    @Excel(name = "姓名")
    private String name;

    // 身份证号
    @Excel(name = "身份证号", width = 25)
    private String idCard;

    // 设备ID
    @Excel(name = "设备ID", width = 25)
    private String deviceId;

    @ExcelEntity(id = "deptClass")
    private DeptDTO deptClass;

    private Long deptSchoolId;

    @Excel(name = "学校", width = 25)
    private String deptSchool;

    // 家长姓名
    @Excel(name = "家长姓名")
    private String parent;

    // 家长联系方式
    @Excel(name = "家长联系方式", width = 20)
    private String parentPhone;

    // 住校地址
    @Excel(name = "住校地址", width = 25)
    private String address;

    // 房间号
    @Excel(name = "房间号")
    private String room;

    // 责任人
    @Excel(name = "责任人")
    private String alarmPerson;

    // 责任人手机号
    @Excel(name = "责任人手机号")
    private String alarmPhone;

    // 责任人微信号
    @Excel(name = "责任人微信号")
    private String alarmWechat;

    // 通知方式
    @Excel(name = "通知方式")
    private String alarmMethod;

    // 最近一次检测体温
    @Excel(name = "最近一次检测体温")
    private Double recentTemperature;

    // 最近一次检测时间
    @Excel(name = "最近一次检测时间", width = 25, format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp recentTime;

    // 轨迹
    @Excel(name = "轨迹")
    private String loca;

    @Excel(name = "绑定状态", replace = { "已绑定_true", "未绑定_false" }, isImportField = "true_st")
    private String bindStatus;
}
