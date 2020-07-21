package com.skyform.modules.system.service;

import com.skyform.modules.system.domain.MessageLog;
import com.skyform.modules.system.service.dto.MessageLogDTO;
import com.skyform.modules.system.service.dto.MessageLogQueryCriteria;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

/**
* @author renjk
* @date 2020-06-28
*/
//@CacheConfig(cacheNames = "messageLog")
public interface MessageLogService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(MessageLogQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    public Object queryAll(MessageLogQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    MessageLogDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    MessageLogDTO create(MessageLog resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(MessageLog resources);

    /**
     * delete
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Long id);

    int getCountCurrentDate(String phone);

    int getCountOneMinute(String phone);
}