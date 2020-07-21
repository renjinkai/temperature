package com.skyform.modules.system.rest;

import com.skyform.aop.log.Log;
import com.skyform.modules.security.security.AuthenticationInfo;
import com.skyform.modules.security.security.AuthorizationUser;
import com.skyform.modules.security.security.JwtUser;
import com.skyform.modules.security.utils.JwtTokenUtil;
import com.skyform.modules.system.domain.Temperature;
import com.skyform.modules.system.domain.User;
import com.skyform.modules.system.service.AppService;
import com.skyform.modules.system.service.TemperatureService;
import com.skyform.modules.system.service.UserService;
import com.skyform.modules.system.service.dto.DeviceDTO;
import com.skyform.modules.system.service.dto.DeviceQueryCriteria;
import com.skyform.modules.system.service.dto.TemperatureQueryCriteria;
import com.skyform.utils.EncryptUtils;
import com.skyform.utils.UuidUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/app")
public class AppController {

    @Autowired
    private AppService appService;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private TemperatureService temperatureService;

    @Log("发送短信验证码")
    @ApiOperation(value = "发送短信验证码")
    @PostMapping(value = "/sendCode")
    public ResponseEntity sendCode(@RequestBody AuthorizationUser authorizationUser){
        return new ResponseEntity(appService.sendCode(authorizationUser.getPhone()), HttpStatus.OK);
    }

    @Log("用户注册")
    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/userRegister")
    public ResponseEntity userRegister(@RequestBody AuthorizationUser authorizationUser){
        Map<String, String> map = new HashMap<String, String>();
        if(authorizationUser.getPhone() == null || authorizationUser.getUsername() == ""){
            map.put("message", "手机号为空");
            map.put("data", "");
            map.put("code", "-1");
            map.put("time", new Date().toString());
            return new ResponseEntity(map, HttpStatus.OK);
        }
        return new ResponseEntity(appService.userRegister(authorizationUser.getPhone(),authorizationUser.getPhone()
                ,authorizationUser.getPassword(),authorizationUser.getCode()), HttpStatus.CREATED);
    }

    /**
     * 登录授权
     * @param authorizationUser
     * @return
     */
    @Log("APP用户登录")
    @ApiOperation(value = "APP用户登录")
    @PostMapping(value = "/login")
    public Map<String, Object> login(@RequestBody AuthorizationUser authorizationUser){
        Map<String, Object> map = new HashMap<String, Object>();
        User user = userService.findByPhone(authorizationUser.getPhone());
        final JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(user.getUsername());
        if(!jwtUser.getPassword().equals(EncryptUtils.encryptPassword(authorizationUser.getPassword()))){
            throw new AccountExpiredException("密码错误");
        }
        // 生成令牌
        final String token = jwtTokenUtil.generateToken(jwtUser);
        // 返回 token
        map.put("message", "");
        map.put("data", new AuthenticationInfo(token,jwtUser));
        map.put("code", "0");
        map.put("time", new Date().toString());
        return map;
    }

    @Log("密码找回")
    @ApiOperation(value = "密码找回")
    @PostMapping(value = "/resetPassword")
    public ResponseEntity resetPassword(@RequestBody AuthorizationUser authorizationUser){
        return new ResponseEntity(appService.resetPassword(authorizationUser.getPhone(),authorizationUser.getCode(),
                authorizationUser.getPassword()), HttpStatus.OK);
    }

    @Log("修改用户别名")
    @ApiOperation(value = "修改用户别名")
    @PostMapping(value = "/updateCname")
    public ResponseEntity updateCname(@RequestBody User user){
        Map<String, String> map = new HashMap<String, String>();
        if(user.getPhone() == null || user.getPhone() == ""){
            map.put("message", "手机号为空");
            map.put("data", "");
            map.put("code", "-1");
            map.put("time", new Date().toString());
            return new ResponseEntity(map, HttpStatus.OK);
        }
        if(user.getCname() == null || user.getCname() == ""){
            map.put("message", "用户名为空");
            map.put("data", "");
            map.put("code", "-1");
            map.put("time", new Date().toString());
            return new ResponseEntity(map, HttpStatus.OK);
        }
        return new ResponseEntity(appService.updateCname(user.getPhone(),user.getCname()), HttpStatus.OK);
    }

    @Log("设备列表查询")
    @ApiOperation(value = "设备列表查询")
    @GetMapping(value = "/getDevice")
    public ResponseEntity getDevice(@RequestParam long userId){
        return new ResponseEntity(appService.getDevice(userId), HttpStatus.OK);
    }

    @Log("查询设备详情")
    @ApiOperation(value = "查询设备详情")
    @PostMapping(value = "/getDeviceDetail")
    public ResponseEntity getDeviceDetail(@RequestBody DeviceQueryCriteria criteria){
        return new ResponseEntity(appService.getDeviceDetail(criteria), HttpStatus.OK);
    }

    @Log("添加设备")
    @ApiOperation(value = "添加设备")
    @PostMapping(value = "/addDevice")
    public ResponseEntity addDevice(@RequestBody DeviceDTO deviceDTO){
        return new ResponseEntity(appService.addDevice(deviceDTO.getDeviceId(),deviceDTO.getName(),deviceDTO.getPhone()), HttpStatus.OK);
    }

    @Log("删除设备")
    @ApiOperation(value = "删除设备")
    @PostMapping(value = "/removeDevice")
    public ResponseEntity removeDevice(@RequestBody DeviceDTO deviceDTO){
        return new ResponseEntity(appService.removeDevice(deviceDTO.getDeviceId(),deviceDTO.getPhone()), HttpStatus.OK);
    }

    @Log("修改设备")
    @ApiOperation(value = "修改设备")
    @PostMapping(value = "/updateDevice")
    public ResponseEntity updateDevice(@RequestBody DeviceDTO deviceDTO){
        return new ResponseEntity(appService.updateDevice(deviceDTO.getName(),deviceDTO.getDeviceId()), HttpStatus.OK);
    }

    @Log("设备历史温度查询")
    @ApiOperation(value = "设备历史温度查询")
    @PostMapping(value = "/temperature")
    public Map<String, Object> temperature(@RequestBody TemperatureQueryCriteria criteria){
        Map<String, Object> map = new HashMap<String, Object>();
        Sort sort = new Sort(Sort.Direction.DESC, "recordTime");
        Pageable pageable = new PageRequest(criteria.getPage(),criteria.getSize(),sort);
        map.put("message", "");
        map.put("data", temperatureService.queryAll(criteria,pageable));
        map.put("code", "0");
        map.put("time", new Date().toString());
        return map;
    }

    @Log("温度数据上传")
    @ApiOperation(value = "温度数据上传")
    @PostMapping(value = "/uploadTemperature")
    public ResponseEntity uploadTemperature(@RequestBody List<Temperature> temperatureList){
        return new ResponseEntity(appService.uploadTemperature(temperatureList), HttpStatus.OK);
    }

    @Log("用户信息修改")
    @ApiOperation(value = "用户信息修改")
    @PutMapping(value = "/updateUser")
    public ResponseEntity updateUser(@RequestBody User resources){
        userService.update(resources);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Log("生成群组编码")
    @ApiOperation(value = "生成群组编码")
    @GetMapping(value = "/generatorAppGroupCode")
    public Map<String, String> generatorAppGroupCode(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("message", "");
        map.put("data", UuidUtil.genUUID());
        map.put("code", "0");
        map.put("time", new Date().toString());
        return map;
    }
}
