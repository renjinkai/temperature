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
@Table(name="app_group_person_relation")
public class AppGroupPersonRelation implements Serializable {

    // 主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 群组ID
    @Column(name = "group_id")
    private Long groupId;

    // 成员ID
    @Column(name = "user_id")
    private Long userId;

    public void copy(AppGroupPersonRelation source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}