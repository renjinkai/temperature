package com.skyform.modules.system.service;

import com.skyform.modules.system.domain.DeviceMessage;
import com.skyform.modules.system.service.dto.DeviceMessageDTO;
import com.skyform.modules.system.service.dto.DeviceMessageQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
* @author renjk
* @date 2020-07-16
*/
//@CacheConfig(cacheNames = "deviceMessage")
public interface DeviceMessageService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(DeviceMessageQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(DeviceMessageQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    DeviceMessageDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    DeviceMessageDTO create(DeviceMessage resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(DeviceMessage resources);

    /**
     * delete
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Long id);
}