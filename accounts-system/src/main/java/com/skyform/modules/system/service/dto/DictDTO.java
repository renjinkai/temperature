package com.skyform.modules.system.service.dto;

import lombok.Data;

import java.io.Serializable;

import com.skyform.annotation.Query;

@Data
public class DictDTO implements Serializable {

    private Long id;

    /**
     * 字典名称
     */
    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    /**
     * 描述
     */
    @Query(type = Query.Type.INNER_LIKE)
    private String remark;
}
