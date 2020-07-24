package com.skyform.modules.system.rest;

import com.alibaba.fastjson.JSONObject;
import com.skyform.aop.log.Log;
import com.skyform.modules.system.domain.DeviceMessage;
import com.skyform.modules.system.domain.Temperature;
import com.skyform.modules.system.service.DeviceMessageService;
import com.skyform.modules.system.service.TemperatureService;
import com.skyform.modules.system.service.dto.DeviceMessageDTO;
import com.skyform.modules.system.service.dto.DeviceMessageQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
* @author renjk
* @date 2020-07-16
*/
@Api(tags = "DeviceMessage管理")
@RestController
@RequestMapping("api")
public class DeviceMessageController {

    @Autowired
    private DeviceMessageService deviceMessageService;

    @Autowired
    private TemperatureService temperatureService;

    @Log("查询DeviceMessage")
    @ApiOperation(value = "查询DeviceMessage")
    @GetMapping(value = "/deviceMessage")
    @PreAuthorize("hasAnyRole('ADMIN','DEVICEMESSAGE_ALL','DEVICEMESSAGE_SELECT')")
    public ResponseEntity getDeviceMessages(DeviceMessageQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(deviceMessageService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增DeviceMessage")
    @ApiOperation(value = "新增DeviceMessage")
    @PostMapping(value = "/deviceMessage")
    public ResponseEntity create(@RequestBody JSONObject jsonObject){
        DeviceMessage resources = new DeviceMessage();
        if(jsonObject.getString("messageType") != null && !"".equals(jsonObject.getString("messageType"))){
            resources.setMessageType(jsonObject.getString("messageType"));
        }
        if(jsonObject.getString("productId") != null && !"".equals(jsonObject.getString("productId"))){
            resources.setProductId(jsonObject.getString("productId"));
        }
        if(jsonObject.getString("deviceId") != null && !"".equals(jsonObject.getString("deviceId"))){
            resources.setDeviceId(jsonObject.getString("deviceId"));
        }
        if(jsonObject.getString("tenantId") != null && !"".equals(jsonObject.getString("tenantId"))){
            resources.setTenantId(jsonObject.getString("tenantId"));
        }
        if(jsonObject.getString("payload") != null && !"".equals(jsonObject.getString("payload"))){
            resources.setPayLoad(jsonObject.getString("payload"));
        }
        if(jsonObject.getLong("timestamp") != null){
            resources.setDeviceTime(new Timestamp(jsonObject.getLong("timestamp")));
        }
        resources.setMessage(jsonObject.toJSONString());
        resources.setCreateTime(new Timestamp(new Date().getTime()));
        DeviceMessageDTO deviceMessageDTO = deviceMessageService.create(resources);
        //解析设备数据
        List<Temperature> list = temperatureService.analysisData(resources);
        //录入温度表
        if(list.size()>0){
            for (Temperature temperature:list){
                temperatureService.create(temperature);
            }
        }
        return new ResponseEntity(deviceMessageDTO,HttpStatus.OK);
    }

    @Log("修改DeviceMessage")
    @ApiOperation(value = "修改DeviceMessage")
    @PutMapping(value = "/deviceMessage")
    @PreAuthorize("hasAnyRole('ADMIN','DEVICEMESSAGE_ALL','DEVICEMESSAGE_EDIT')")
    public ResponseEntity update(@Validated @RequestBody DeviceMessage resources){
        deviceMessageService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除DeviceMessage")
    @ApiOperation(value = "删除DeviceMessage")
    @DeleteMapping(value = "/deviceMessage/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DEVICEMESSAGE_ALL','DEVICEMESSAGE_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        deviceMessageService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
