package com.skyform.modules.system.service;

import com.skyform.modules.system.domain.AppGroupPersonRelation;
import com.skyform.modules.system.service.dto.AppGroupPersonRelationDTO;
import com.skyform.modules.system.service.dto.AppGroupPersonRelationQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
* @author renjk
* @date 2020-06-19
*/
//@CacheConfig(cacheNames = "appGroupPersonRelation")
public interface AppGroupPersonRelationService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(AppGroupPersonRelationQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(AppGroupPersonRelationQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    AppGroupPersonRelationDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    AppGroupPersonRelationDTO create(AppGroupPersonRelation resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(AppGroupPersonRelation resources);

    /**
     * delete
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Long id);
}