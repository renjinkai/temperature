package com.skyform.modules.system.rest;

import com.skyform.aop.log.Log;
import com.skyform.modules.system.domain.AppPersonDeviceRelation;
import com.skyform.modules.system.service.AppPersonDeviceRelationService;
import com.skyform.modules.system.service.dto.AppPersonDeviceRelationQueryCriteria;
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
@Api(tags = "AppPersonDeviceRelation管理")
@RestController
@RequestMapping("api")
public class AppPersonDeviceRelationController {

    @Autowired
    private AppPersonDeviceRelationService appPersonDeviceRelationService;

    @Log("查询AppPersonDeviceRelation")
    @ApiOperation(value = "查询AppPersonDeviceRelation")
    @GetMapping(value = "/appPersonDeviceRelation")
    @PreAuthorize("hasAnyRole('ADMIN','APPPERSONDEVICERELATION_ALL','APPPERSONDEVICERELATION_SELECT')")
    public ResponseEntity getAppPersonDeviceRelations(AppPersonDeviceRelationQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(appPersonDeviceRelationService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增AppPersonDeviceRelation")
    @ApiOperation(value = "新增AppPersonDeviceRelation")
    @PostMapping(value = "/appPersonDeviceRelation")
    @PreAuthorize("hasAnyRole('ADMIN','APPPERSONDEVICERELATION_ALL','APPPERSONDEVICERELATION_CREATE')")
    public ResponseEntity create(@Validated @RequestBody AppPersonDeviceRelation resources){
        return new ResponseEntity(appPersonDeviceRelationService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改AppPersonDeviceRelation")
    @ApiOperation(value = "修改AppPersonDeviceRelation")
    @PutMapping(value = "/appPersonDeviceRelation")
    @PreAuthorize("hasAnyRole('ADMIN','APPPERSONDEVICERELATION_ALL','APPPERSONDEVICERELATION_EDIT')")
    public ResponseEntity update(@Validated @RequestBody AppPersonDeviceRelation resources){
        appPersonDeviceRelationService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除AppPersonDeviceRelation")
    @ApiOperation(value = "删除AppPersonDeviceRelation")
    @DeleteMapping(value = "/appPersonDeviceRelation/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','APPPERSONDEVICERELATION_ALL','APPPERSONDEVICERELATION_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        appPersonDeviceRelationService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}