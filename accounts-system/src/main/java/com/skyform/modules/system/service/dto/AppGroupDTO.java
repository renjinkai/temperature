package com.skyform.modules.system.service.dto;

import lombok.Data;
import java.io.Serializable;


/**
* @author renjk
* @date 2020-06-19
*/
@Data
public class AppGroupDTO implements Serializable {

    // 主键ID
    private Long id;

    // 群组名称
    private String name;

    // 群组编码
    private String code;

    // 创建人
    private String createBy;

    // 创建人联系方式
    private String contact;
}