package com.skyform.modules.system.service;

import com.skyform.modules.system.domain.AppPersonDeviceRelation;
import com.skyform.modules.system.service.dto.AppPersonDeviceRelationDTO;
import com.skyform.modules.system.service.dto.AppPersonDeviceRelationQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
* @author renjk
* @date 2020-06-19
*/
//@CacheConfig(cacheNames = "appPersonDeviceRelation")
public interface AppPersonDeviceRelationService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(AppPersonDeviceRelationQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    List<AppPersonDeviceRelationDTO> queryAll(AppPersonDeviceRelationQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    AppPersonDeviceRelationDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    AppPersonDeviceRelationDTO create(AppPersonDeviceRelation resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(AppPersonDeviceRelation resources);

    /**
     * delete
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Long id);
}