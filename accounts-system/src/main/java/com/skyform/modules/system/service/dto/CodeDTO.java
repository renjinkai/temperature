package com.skyform.modules.system.service.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CodeDTO implements Serializable {
    private Long deptId;
    List<String> codeList;
}