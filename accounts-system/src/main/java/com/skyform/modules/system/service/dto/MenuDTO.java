package com.skyform.modules.system.service.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
public class MenuDTO {

    private Long id;

    private String name;

    private Long sort;

    private String path;

    private String component;

    private Long pid;

    private Boolean iFrame;

    private String icon;

    private List<MenuDTO> children;

    private Timestamp createTime;
}
