package com.skyform.modules.system.rest;

import com.alibaba.fastjson.JSONObject;
import com.skyform.aop.log.Log;
import com.skyform.modules.system.domain.AppGroup;
import com.skyform.modules.system.service.AppGroupService;
import com.skyform.modules.system.service.dto.AppGroupDTO;
import com.skyform.modules.system.service.dto.AppGroupQueryCriteria;
import com.skyform.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author renjk
* @date 2020-06-19
*/
@Api(tags = "AppGroup管理")
@RestController
@RequestMapping("api")
public class AppGroupController {

    @Autowired
    private AppGroupService appGroupService;

    @Log("查询AppGroup")
    @ApiOperation(value = "查询AppGroup")
    @GetMapping(value = "/appGroup")
    @PreAuthorize("hasAnyRole('ADMIN','APPGROUP_ALL','APPGROUP_SELECT')")
    public ResponseEntity getAppGroups(AppGroupQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(appGroupService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增AppGroup")
    @ApiOperation(value = "新增AppGroup")
    @PostMapping(value = "/appGroup")
    @PreAuthorize("hasAnyRole('ADMIN','APPGROUP_ALL','APPGROUP_CREATE')")
    public ResponseEntity create(@Validated @RequestBody AppGroup resources){
        return new ResponseEntity(appGroupService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改AppGroup")
    @ApiOperation(value = "修改AppGroup")
    @PutMapping(value = "/appGroup")
    @PreAuthorize("hasAnyRole('ADMIN','APPGROUP_ALL','APPGROUP_EDIT')")
    public ResponseEntity update(@Validated @RequestBody AppGroup resources){
        appGroupService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除AppGroup")
    @ApiOperation(value = "删除AppGroup")
    @DeleteMapping(value = "/appGroup/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','APPGROUP_ALL','APPGROUP_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        appGroupService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("加入AppGroup")
    @ApiOperation(value = "加入AppGroup")
    @PostMapping(value = "/inAppGroup")
    public ResponseEntity inAppGroup(@RequestBody JSONObject jsonObject){
        return new ResponseEntity(appGroupService.inAppGroup(jsonObject.getString("code"),jsonObject.getLong("userId")),HttpStatus.OK);
    }

    @Log("统计群组人员数量、关联设备数量")
    @ApiOperation(value = "统计群组人员数量、关联设备数量")
    @GetMapping(value = "/appGroupCount")
    public ResponseEntity appGroupCount(@RequestParam long groupId){
        return new ResponseEntity(appGroupService.appGroupCount(groupId),HttpStatus.OK);
    }

    @Log("生成群组编码")
    @ApiOperation(value = "生成群组编码")
    @GetMapping(value = "/generatorAppGroupCode")
    public Map<String, String> generatorAppGroupCode(){
        Map<String, String> map = new HashMap<String, String>();
        String code;
        int size;
        do {
            code = UuidUtil.genNum();
            AppGroupQueryCriteria criteria = new AppGroupQueryCriteria();
            criteria.setCode(code);
            List<AppGroupDTO> list = appGroupService.queryAll(criteria);
            size = list.size();
        }while (size>0);
        map.put("message", "");
        map.put("data", code);
        map.put("code", "0");
        map.put("time", new Date().toString());
        return map;
    }
}
