package com.skyform.modules.system.rest;

import com.skyform.aop.log.Log;
import com.skyform.config.DataScope;
import com.skyform.modules.system.domain.Temperature;
import com.skyform.modules.system.service.DeviceService;
import com.skyform.modules.system.service.TemperatureService;
import com.skyform.modules.system.service.dto.TemperatureDTO;
import com.skyform.modules.system.service.dto.TemperatureQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author renjk
* @date 2020-05-29
*/
@Api(tags = "Temperature管理")
@RestController
@RequestMapping("api")
public class TemperatureController {

    @Autowired
    private TemperatureService temperatureService;

    @Autowired
    private DataScope dataScope;

    @Log("查询Temperature")
    @ApiOperation(value = "查询Temperature")
    @GetMapping(value = "/temperature")
    @PreAuthorize("hasAnyRole('ADMIN','TEMPERATURE_ALL','TEMPERATURE_SELECT')")
    public ResponseEntity getTemperatures(TemperatureQueryCriteria criteria, Pageable pageable){
        // 数据权限
        criteria.setDeptIds(dataScope.getDeptIds());
        return new ResponseEntity(temperatureService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增Temperature")
    @ApiOperation(value = "新增Temperature")
    @PostMapping(value = "/temperature")
    @PreAuthorize("hasAnyRole('ADMIN','TEMPERATURE_ALL','TEMPERATURE_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Temperature resources){
        return new ResponseEntity(temperatureService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Temperature")
    @ApiOperation(value = "修改Temperature")
    @PutMapping(value = "/temperature")
    @PreAuthorize("hasAnyRole('ADMIN','TEMPERATURE_ALL','TEMPERATURE_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Temperature resources){
        temperatureService.update(resources);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("删除Temperature")
    @ApiOperation(value = "删除Temperature")
    @DeleteMapping(value = "/temperature/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','TEMPERATURE_ALL','TEMPERATURE_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        temperatureService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("myBatis查询Temperature")
    @ApiOperation(value = "myBatis查询Temperature")
    @GetMapping(value = "/myBatisTemperature")
    @PreAuthorize("hasAnyRole('ADMIN','TEMPERATURE_ALL','MYBATIS_TEMPERATURE_SELECT')")
    public List<TemperatureDTO> myBatisTemperature(TemperatureQueryCriteria criteria){
        return temperatureService.query(criteria);
    }

    @Log("异常人数统计")
    @ApiOperation(value = "异常人数统计")
    @PostMapping(value = "/countAbnormal")
    @PreAuthorize("hasAnyRole('ADMIN','TEMPERATURE_ALL','TEMPERATURE_COUNT')")
    public ResponseEntity countAbnormal(){
        return new ResponseEntity(temperatureService.countAbnormal(),HttpStatus.OK);
    }
}
