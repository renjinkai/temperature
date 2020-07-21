package com.skyform.modules.system.rest;

import com.skyform.aop.log.Log;
import com.skyform.modules.system.domain.AppGroupPersonRelation;
import com.skyform.modules.system.service.AppGroupPersonRelationService;
import com.skyform.modules.system.service.dto.AppGroupPersonRelationQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
* @author renjk
* @date 2020-06-19
*/
@Api(tags = "AppGroupPersonRelation管理")
@RestController
@RequestMapping("api")
public class AppGroupPersonRelationController {

    @Autowired
    private AppGroupPersonRelationService appGroupPersonRelationService;

    @Log("查询AppGroupPersonRelation")
    @ApiOperation(value = "查询AppGroupPersonRelation")
    @GetMapping(value = "/appGroupPersonRelation")
    @PreAuthorize("hasAnyRole('ADMIN','APPGROUPPERSONRELATION_ALL','APPGROUPPERSONRELATION_SELECT')")
    public ResponseEntity getAppGroupPersonRelations(AppGroupPersonRelationQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(appGroupPersonRelationService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增AppGroupPersonRelation")
    @ApiOperation(value = "新增AppGroupPersonRelation")
    @PostMapping(value = "/appGroupPersonRelation")
    @PreAuthorize("hasAnyRole('ADMIN','APPGROUPPERSONRELATION_ALL','APPGROUPPERSONRELATION_CREATE')")
    public ResponseEntity create(@Validated @RequestBody AppGroupPersonRelation resources){
        return new ResponseEntity(appGroupPersonRelationService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改AppGroupPersonRelation")
    @ApiOperation(value = "修改AppGroupPersonRelation")
    @PutMapping(value = "/appGroupPersonRelation")
    @PreAuthorize("hasAnyRole('ADMIN','APPGROUPPERSONRELATION_ALL','APPGROUPPERSONRELATION_EDIT')")
    public ResponseEntity update(@Validated @RequestBody AppGroupPersonRelation resources){
        appGroupPersonRelationService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除AppGroupPersonRelation")
    @ApiOperation(value = "删除AppGroupPersonRelation")
    @DeleteMapping(value = "/appGroupPersonRelation/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','APPGROUPPERSONRELATION_ALL','APPGROUPPERSONRELATION_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        appGroupPersonRelationService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}