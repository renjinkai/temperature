package com.skyform.modules.system.service;

import com.alibaba.fastjson.JSONObject;
import com.skyform.modules.system.domain.Device;
import com.skyform.modules.system.service.dto.DeviceDTO;
import com.skyform.modules.system.service.dto.DeviceQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
* @author renjk
* @date 2020-06-01
*/
//@CacheConfig(cacheNames = "device")
public interface DeviceService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(DeviceQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public List<DeviceDTO> queryAll(DeviceQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    DeviceDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    DeviceDTO create(Device resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Device resources);

    /**
     * delete
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Long id);

    /**
     * updateByDeviceId
     * @param device
     */
    void updateByDeviceId(Device device);

    /**
     * setNull
     * @param device
     */
    void setNull(Device device);

    /**
     * bind
     */
    void bind(String deviceId, String idCard, String name);

    /**
     * unbind
     */
    void unbind(String deviceId, String idCard);

    Device findByDeviceId(String deviceId);

    void updateNameByDeviceId(String name, String deviceId);

    List<Device> getDeviceByUserId(long userId);
}