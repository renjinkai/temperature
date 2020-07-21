package com.skyform.modules.system.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @author renjk
* @date 2020-06-28
*/
@Entity
@Data
@Table(name="message_log")
public class MessageLog implements Serializable {

    // 主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 手机号
    @Column(name = "phone")
    private String phone;

    // 短信发送时间
    @Column(name = "send_time")
    private Timestamp sendTime;

    // 验证码
    @Column(name = "message")
    private String message;

    public void copy(MessageLog source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}