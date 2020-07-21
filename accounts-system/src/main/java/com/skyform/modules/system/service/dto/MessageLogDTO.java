package com.skyform.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;


/**
* @author renjk
* @date 2020-06-28
*/
@Data
public class MessageLogDTO implements Serializable {

    // 主键ID
    private Long id;

    // 手机号
    private String phone;

    // 短信发送时间
    private Timestamp sendTime;

    // 验证码
    private String message;
}