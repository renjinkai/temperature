package com.skyform.modules.system.service;

import com.skyform.modules.system.domain.Temperature;
import com.skyform.modules.system.service.dto.AbnormalDTO;
import com.skyform.modules.system.service.dto.TemperatureDTO;
import com.skyform.modules.system.service.dto.TemperatureQueryCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;

//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author renjk
* @date 2020-05-29
*/
//@CacheConfig(cacheNames = "temperature")
public interface TemperatureService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(TemperatureQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    List<TemperatureDTO> queryAll(TemperatureQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    TemperatureDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    TemperatureDTO create(Temperature resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Temperature resources);

    /**
     * delete
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Long id);

    /**
     * myBatis query
     * @param criteria
     */
    List<TemperatureDTO> query(TemperatureQueryCriteria criteria);

    void deleteByDeviceId(String deviceId);

    List<AbnormalDTO> countAbnormal();
}
