package com.skyform.service.dto;

import com.skyform.annotation.Query;

import lombok.Data;

/**
 * sm.ms图床
 */
@Data
public class PictureQueryCriteria{

    @Query(type = Query.Type.INNER_LIKE)
    private String filename;
    
    @Query(type = Query.Type.INNER_LIKE)
    private String username;
}
