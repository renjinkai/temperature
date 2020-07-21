package com.skyform.modules.system.rest;

import com.skyform.aop.log.Log;
import com.skyform.modules.system.domain.MessageLog;
import com.skyform.modules.system.service.MessageLogService;
import com.skyform.modules.system.service.dto.MessageLogQueryCriteria;
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
* @date 2020-06-28
*/
@Api(tags = "MessageLog管理")
@RestController
@RequestMapping("api")
public class MessageLogController {

    @Autowired
    private MessageLogService messageLogService;

    @Log("查询MessageLog")
    @ApiOperation(value = "查询MessageLog")
    @GetMapping(value = "/messageLog")
    @PreAuthorize("hasAnyRole('ADMIN','MESSAGELOG_ALL','MESSAGELOG_SELECT')")
    public ResponseEntity getMessageLogs(MessageLogQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(messageLogService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增MessageLog")
    @ApiOperation(value = "新增MessageLog")
    @PostMapping(value = "/messageLog")
    @PreAuthorize("hasAnyRole('ADMIN','MESSAGELOG_ALL','MESSAGELOG_CREATE')")
    public ResponseEntity create(@Validated @RequestBody MessageLog resources){
        return new ResponseEntity(messageLogService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改MessageLog")
    @ApiOperation(value = "修改MessageLog")
    @PutMapping(value = "/messageLog")
    @PreAuthorize("hasAnyRole('ADMIN','MESSAGELOG_ALL','MESSAGELOG_EDIT')")
    public ResponseEntity update(@Validated @RequestBody MessageLog resources){
        messageLogService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除MessageLog")
    @ApiOperation(value = "删除MessageLog")
    @DeleteMapping(value = "/messageLog/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MESSAGELOG_ALL','MESSAGELOG_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        messageLogService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}