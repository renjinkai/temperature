package com.skyform.modules.system.service.dto;

import lombok.Data;
import java.io.Serializable;


/**
* @author renjk
* @date 2020-06-19
*/
@Data
public class AppGroupPersonRelationDTO implements Serializable {

    // 主键ID
    private Long id;

    // 群组ID
    private Long groupId;

    // 成员ID
    private Long userId;
}