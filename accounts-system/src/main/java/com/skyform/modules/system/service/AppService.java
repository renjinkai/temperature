package com.skyform.modules.system.service;

import com.skyform.modules.system.domain.Device;
import com.skyform.modules.system.domain.Temperature;
import com.skyform.modules.system.service.dto.DeviceQueryCriteria;

import java.util.List;
import java.util.Map;

/**
* @author renjk
* @date 2020-06-20
*/
public interface AppService {
    Map<String, String> sendCode(String phone);

    Map<String, String> userRegister(String phone, String username, String password, String code);

    Map<String, String> resetPassword(String phone, String code, String newPassword);

    Map<String, String> updateCname(String phone, String cname);

    Map<String, Object> getDevice(long userId);

    Map<String, Object> getDeviceDetail(DeviceQueryCriteria criteria);

    Map<String, String> addDevice(String deviceId, String name, String phone);

    Map<String, String> removeDevice(String deviceId, String phone);

    Map<String, String> updateDevice(String name, String deviceId);

    Map<String, String> uploadTemperature(List<Temperature> temperatureList);
}