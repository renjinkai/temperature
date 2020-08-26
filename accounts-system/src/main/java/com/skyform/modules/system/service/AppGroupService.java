package com.skyform.modules.system.service;

import com.skyform.modules.system.domain.AppGroup;
import com.skyform.modules.system.service.dto.AppGroupDTO;
import com.skyform.modules.system.service.dto.AppGroupQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
* @author renjk
* @date 2020-06-19
*/
//@CacheConfig(cacheNames = "appGroup")
public interface AppGroupService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(AppGroupQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    List<AppGroupDTO> queryAll(AppGroupQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    AppGroupDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    AppGroupDTO create(AppGroup resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(AppGroup resources);

    /**
     * delete
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Long id);

    AppGroup findByCode(String code);

    Map<String, String> inAppGroup(String code, long userId);

    Map<String, Object> appGroupCount(long groupId);

    List<Object> getAppGroupUsers(long groupId);
}
