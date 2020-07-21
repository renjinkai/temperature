package com.skyform.modules.system.rest;

import com.alibaba.fastjson.JSONObject;
import com.skyform.aop.log.Log;
import com.skyform.modules.system.domain.Device;
import com.skyform.modules.system.service.DeviceService;
import com.skyform.modules.system.service.dto.DeviceDTO;
import com.skyform.modules.system.service.dto.DeviceQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author renjk
* @date 2020-06-01
*/
@Api(tags = "Device管理")
@RestController
@RequestMapping("api")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Log("查询Device")
    @ApiOperation(value = "查询Device")
    @GetMapping(value = "/device")
    @PreAuthorize("hasAnyRole('ADMIN','DEVICE_ALL','DEVICE_SELECT')")
    public ResponseEntity getDevices(DeviceQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(deviceService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增Device")
    @ApiOperation(value = "新增Device")
    @PostMapping(value = "/device")
    @PreAuthorize("hasAnyRole('ADMIN','DEVICE_ALL','DEVICE_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Device resources){
        return new ResponseEntity(deviceService.create(resources),HttpStatus.CREATED);
    }

    @Log("新增DeviceList")
    @ApiOperation(value = "新增DeviceList")
    @PostMapping(value = "/deviceList")
    @PreAuthorize("hasAnyRole('ADMIN','DEVICE_ALL','DEVICE_CREATE')")
    public ResponseEntity create(@Validated @RequestBody List<Device> resourcesList){
        List<DeviceDTO> list = new ArrayList<DeviceDTO>();
        for(Device resource : resourcesList){
            list.add(deviceService.create(resource));
        }
        return new ResponseEntity(list,HttpStatus.CREATED);
    }

    @Log("修改Device")
    @ApiOperation(value = "修改Device")
    @PutMapping(value = "/device")
    @PreAuthorize("hasAnyRole('ADMIN','DEVICE_ALL','DEVICE_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Device resources){
        deviceService.update(resources);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("删除Device")
    @ApiOperation(value = "删除Device")
    @DeleteMapping(value = "/device/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DEVICE_ALL','DEVICE_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        deviceService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("绑定Device")
    @ApiOperation(value = "绑定Device")
    @PostMapping(value = "/device/bind")
    @PreAuthorize("hasAnyRole('ADMIN','DEVICE_ALL','DEVICE_BIND')")
    public ResponseEntity bind(@RequestBody List<JSONObject> list){
        Map<String, String> map = new HashMap<String, String>();
        if(list.size() > 0){
            for(JSONObject jsonObject : list){
                DeviceQueryCriteria criteria = new DeviceQueryCriteria();
                criteria.setDeviceId(jsonObject.getString("deviceId"));
                List<DeviceDTO> deviceDTOs = deviceService.queryAll(criteria);
                if(deviceDTOs.size() > 0){
                    deviceService.bind(jsonObject.getString("deviceId"),jsonObject.getString("idCard"),jsonObject.getString("name"));
                }else{
                    map.put("message", "此设备"+jsonObject.getString("deviceId")+"不存在");
                    return new ResponseEntity(map,HttpStatus.METHOD_NOT_ALLOWED);
                }
            }
            map.put("message", "已绑定");
            return new ResponseEntity(map,HttpStatus.OK);
        }else{
            map.put("message", "绑定列表为空");
            return new ResponseEntity(map,HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @Log("解绑Device")
    @ApiOperation(value = "解绑Device")
    @PostMapping(value = "/device/unbind")
    @PreAuthorize("hasAnyRole('ADMIN','DEVICE_ALL','DEVICE_UNBIND')")
    public ResponseEntity unbind(@ApiParam("设备ID") @RequestParam(value = "deviceId") String deviceId,
                               @ApiParam("身份证号") @RequestParam(value = "idCard") String idCard){
        Map<String, String> map = new HashMap<String, String>();
        DeviceQueryCriteria criteria = new DeviceQueryCriteria();
        criteria.setDeviceId(deviceId);
        List<DeviceDTO> deviceDTOs = deviceService.queryAll(criteria);
        if(deviceDTOs.size() > 0){
            deviceService.unbind(deviceId, idCard);
            map.put("message", "已解绑");
            return new ResponseEntity(map,HttpStatus.OK);
        }else{
            map.put("message", "此设备"+deviceId+"不存在");
            return new ResponseEntity(map,HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
}
