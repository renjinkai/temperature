package com.skyform.modules.system.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import java.io.Serializable;

/**
* @author renjk
* @date 2020-06-19
*/
@Entity
@Data
@Table(name="app_group")
public class AppGroup implements Serializable {

    // 主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 群组名称
    @Column(name = "name")
    private String name;

    // 群组编码
    @Column(name = "code")
    private String code;

    // 创建人
    @Column(name = "create_by")
    private String createBy;

    // 创建人联系方式
    @Column(name = "contact")
    private String contact;

    public void copy(AppGroup source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}