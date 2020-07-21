package com.skyform.modules.system.service.dto;

import com.skyform.modules.system.domain.Dept;
import lombok.Data;

import java.io.Serializable;


/**
* @author renjk
* @date 2020-07-21
*/
@Data
public class AbnormalDTO implements Serializable {

    private Dept dept;

    private int count;
}
