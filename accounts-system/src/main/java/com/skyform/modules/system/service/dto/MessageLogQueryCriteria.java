package com.skyform.modules.system.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import com.skyform.annotation.Query;

/**
* @author renjk
* @date 2020-06-28
*/
@Data
public class MessageLogQueryCriteria{

    // 精确
    @Query
    private Long id;

    // 精确
    @Query
    private String phone;
}