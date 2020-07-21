package com.skyform.modules.system.service.dto;

import lombok.Data;
import com.skyform.annotation.Query;

import java.util.Set;

/**
* @author renjk
* @date 2020-05-29
*/
@Data
public class StudentQueryCriteria{

    // 精确
    @Query
    private Long id;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String name;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String idCard;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String deviceId;

    @Query(propName = "id", joinName = "dept")
    private Long deptId;

    @Query(propName = "id", joinName = "dept", type = Query.Type.IN)
    private Set<Long> deptIds;

    @Query
    private String deptSchool;

    @Query
    private Long deptSchoolId;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String parent;

    // 精确
    @Query
    private String parentPhone;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String address;

    // 模糊
    @Query(type = Query.Type.INNER_LIKE)
    private String room;

    // 精确
    @Query
    private String alarmPhone;

    @Query
    private String bindStatus;
}
