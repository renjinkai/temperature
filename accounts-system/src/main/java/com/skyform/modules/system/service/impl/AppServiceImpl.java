package com.skyform.modules.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.skyform.exception.BadRequestException;
import com.skyform.modules.monitor.service.RedisService;
import com.skyform.modules.security.utils.VerifyCodeUtils;
import com.skyform.modules.system.domain.*;
import com.skyform.modules.system.service.*;
import com.skyform.modules.system.service.dto.AppPersonDeviceRelationDTO;
import com.skyform.modules.system.service.dto.AppPersonDeviceRelationQueryCriteria;
import com.skyform.modules.system.service.dto.DeviceDTO;
import com.skyform.modules.system.service.dto.DeviceQueryCriteria;
import com.skyform.utils.EncryptUtils;
import com.skyform.utils.MessageUtil;
import com.skyform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author renjk
 * @date 2020-06-20
 */
@Service
public class AppServiceImpl implements AppService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private TemperatureService temperatureService;

    @Autowired
    private AppPersonDeviceRelationService appPersonDeviceRelationService;

    @Autowired
    private MessageLogService messageLogService;

    @Override
    public Map<String, String> sendCode(String phone) {
        Map<String, String> map = new HashMap<String, String>();
        // 发送次数：一天发送短信不超过 5 次
        int countCurrentDate = messageLogService.getCountCurrentDate(phone);
        if (countCurrentDate > 5){
            map.put("message", "当天此手机号"+phone+"发送短信超过5次，不能再次发送");
            map.put("data", "");
            map.put("code", "-1");
            map.put("time", new Date().toString());
            return map;
        }
        // 发送频率：1分钟内不能再次发送
        int countOneMinute = messageLogService.getCountOneMinute(phone);
        if(countOneMinute > 0){
            map.put("message", "操作过于频繁，请1分钟后再试");
            map.put("data", "");
            map.put("code", "-1");
            map.put("time", new Date().toString());
            return map;
        }
        int verifyCode = (int) ((Math.random()*9+1)*1000);
        String sms_text = "短信验证码为：" + verifyCode;
        redisService.set(phone, verifyCode, 60 * 5);
        // 插入短信日志
        MessageLog resources = new MessageLog();
        resources.setPhone(phone);
        resources.setMessage(sms_text);
        resources.setSendTime(new Timestamp(new Date().getTime()));
        messageLogService.create(resources);
        return MessageUtil.sendMessage(sms_text, phone);
    }

    @Override
    public Map<String, String> userRegister(String phone, String username, String password, String code) {
        Map<String, String> map = new HashMap<String, String>();
        User user = userService.findByPhone(phone);
        if(user != null){
            map.put("message", "手机号已被注册");
            map.put("data", "");
            map.put("code", "-1");
            map.put("time", new Date().toString());
            return map;
        }
        // 短信验证
        String verifyCode = redisService.getCodeVal(phone);
        if (StringUtils.isBlank(verifyCode)) {
            map.put("message", "验证码已过期");
            map.put("data", "");
            map.put("code", "-1");
            map.put("time", new Date().toString());
            return map;
        }
        if (StringUtils.isBlank(code) || !code.equalsIgnoreCase(verifyCode)) {
            map.put("message", "验证码错误");
            map.put("data", "");
            map.put("code", "-1");
            map.put("time", new Date().toString());
            return map;
        }
        // 插入用户表
        User resource = new User();
        resource.setUsername(username);
        resource.setPassword(EncryptUtils.encryptPassword(password));
        resource.setPhone(phone);
        userService.create(resource);
        map.put("message", "用户"+username+"注册成功");
        map.put("data", "");
        map.put("code", "0");
        map.put("time", new Date().toString());
        return map;
    }

    @Override
    public Map<String, String> resetPassword(String phone, String code, String newPassword) {
        Map<String, String> map = new HashMap<String, String>();
        User user = userService.findByPhone(phone);
        if(user == null){
            map.put("message", "用户不存在");
            map.put("data", "");
            map.put("code", "-1");
            map.put("time", new Date().toString());
            return map;
        }
        // 短信验证
        String verifyCode = redisService.getCodeVal(phone);
        if (StringUtils.isBlank(code)) {
            map.put("message", "验证码已过期");
            map.put("data", "");
            map.put("code", "-1");
            map.put("time", new Date().toString());
            return map;
        }
        if (StringUtils.isBlank(code) || !code.equalsIgnoreCase(verifyCode)) {
            map.put("message", "验证码错误");
            map.put("data", "");
            map.put("code", "-1");
            map.put("time", new Date().toString());
            return map;
        }
        userService.updatePass(user.getUsername(), EncryptUtils.encryptPassword(newPassword));
        map.put("message", "密码重置成功");
        map.put("data", "");
        map.put("code", "0");
        map.put("time", new Date().toString());
        return map;
    }

    @Override
    public Map<String, String> updateCname(String phone, String cname) {
        Map<String, String> map = new HashMap<String, String>();
        userService.updateCname(phone,cname);
        map.put("message", "修改用户名称成功");
        map.put("data", "");
        map.put("code", "0");
        map.put("time", new Date().toString());
        return map;
    }

    @Override
    public Map<String, Object> getDevice(long userId) {
        Map<String, Object> map = new HashMap();
        List<Device> list = deviceService.getDeviceByUserId(userId);
        map.put("message", "");
        map.put("data", list);
        map.put("code", "0");
        map.put("time", new Date().toString());
        return map;
    }

    @Override
    public Map<String, Object> getDeviceDetail(DeviceQueryCriteria criteria) {
        Map<String, Object> map = new HashMap();
        map.put("message", "");
        map.put("data", deviceService.queryAll(criteria));
        map.put("code", "0");
        map.put("time", new Date().toString());
        return map;
    }

    @Override
    public Map<String, String> addDevice(String deviceId, String name, String phone) {
        Map<String, String> map = new HashMap<String, String>();
        long id;
        long userId;
        Device device = deviceService.findByDeviceId(deviceId);
        if(device == null){
            // 创建新设备
            Device resource = new Device();
            resource.setDeviceId(deviceId);
            resource.setBindTime(new Timestamp(new Date().getTime()));
            resource.setName(name);
            resource.setStatus("true");
            DeviceDTO deviceDTO = deviceService.create(resource);
            id = deviceDTO.getId();
        }else{
            // 更新设备信息
            id = device.getId();
            Device resource = new Device();
            resource.setId(id);
            resource.setDeviceId(deviceId);
            resource.setBindTime(resource.getBindTime());
            resource.setName(name);
            resource.setStatus(resource.getStatus());
            deviceService.update(resource);
        }
        User user = userService.findByPhone(phone);
        if(user == null){
            map.put("message", "用户不存在");
            map.put("data", "");
            map.put("code", "-1");
            map.put("time", new Date().toString());
            return map;
        }else{
            userId = user.getId();
        }
        // 绑定用户、设备关系
        AppPersonDeviceRelationQueryCriteria criteria = new AppPersonDeviceRelationQueryCriteria();
        criteria.setUserId(userId);
        criteria.setDeviceId(id);
        List<AppPersonDeviceRelationDTO> list = appPersonDeviceRelationService.queryAll(criteria);
        if(list.size() == 0){
            AppPersonDeviceRelation appPersonDeviceRelation = new AppPersonDeviceRelation();
            appPersonDeviceRelation.setDeviceId(id);
            appPersonDeviceRelation.setUserId(userId);
            appPersonDeviceRelationService.create(appPersonDeviceRelation);
        }else{
            map.put("message", "用户已添加过此设备");
            map.put("data", "");
            map.put("code", "-1");
            map.put("time", new Date().toString());
            return map;
        }
        map.put("message", "添加设备成功");
        map.put("data", "");
        map.put("code", "0");
        map.put("time", new Date().toString());
        return map;
    }

    @Override
    public Map<String, String> removeDevice(String deviceId, String phone) {
        Map<String, String> map = new HashMap<String, String>();
        // 删除设备、监控人关系
        long id;
        long userId;
        Device device = deviceService.findByDeviceId(deviceId);
        if(device != null){
            id = deviceService.findByDeviceId(deviceId).getId();
        }else{
            map.put("message", deviceId+"设备不存在");
            map.put("data", "");
            map.put("code", "-1");
            map.put("time", new Date().toString());
            return map;
        }
        if(userService.findByPhone(phone) != null){
            userId = userService.findByPhone(phone).getId();
        }else{
            map.put("message", phone+"用户不存在");
            map.put("data", "");
            map.put("code", "-1");
            map.put("time", new Date().toString());
            return map;
        }
        AppPersonDeviceRelationQueryCriteria criteria = new AppPersonDeviceRelationQueryCriteria();
        criteria.setDeviceId(id);
        criteria.setUserId(userId);
        List<AppPersonDeviceRelationDTO> list = appPersonDeviceRelationService.queryAll(criteria);
        if(list.size() > 0){
            appPersonDeviceRelationService.delete(list.get(0).getId());
        }else{
            map.put("message", id+"设备与"+userId+"用户关系不存在");
            map.put("data", "");
            map.put("code", "-1");
            map.put("time", new Date().toString());
            return map;
        }
        map.put("message", "删除设备成功");
        map.put("data", "");
        map.put("code", "0");
        map.put("time", new Date().toString());
        return map;
    }

    @Override
    public Map<String, String> updateDevice(String name, String deviceId) {
        Map<String, String> map = new HashMap<String, String>();
        Device device = deviceService.findByDeviceId(deviceId);
        if(device==null){
            map.put("message", deviceId+"设备不存在");
            map.put("data", "");
            map.put("code", "-1");
            map.put("time", new Date().toString());
            return map;
        }
        deviceService.updateNameByDeviceId(name, deviceId);
        map.put("message", "更新设备成功");
        map.put("data", "");
        map.put("code", "0");
        map.put("time", new Date().toString());
        return map;
    }

    @Override
    public Map<String, String> uploadTemperature(List<Temperature> temperatureList) {
        Map<String, String> map = new HashMap<String, String>();
        for(Temperature temperature : temperatureList){
            temperature.setCreateTime(new Timestamp(new Date().getTime()));
            // 判断设备是否已经添加
            Device device = deviceService.findByDeviceId(temperature.getDeviceId());
            if(device == null){
                // 添加设备
                this.addDevice(temperature.getDeviceId(), temperature.getName(), temperature.getPhone());

            }
            temperatureService.create(temperature);
        }
        map.put("message", "温度数据上传成功");
        map.put("data", "");
        map.put("code", "0");
        map.put("time", new Date().toString());
        return map;
    }
}
