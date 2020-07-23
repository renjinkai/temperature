package com.skyform.modules.system.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.skyform.modules.system.domain.Dept;
import com.skyform.modules.system.service.dto.DeptDTO;
import com.skyform.modules.system.service.dto.DeptQueryCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

//@CacheConfig(cacheNames = "dept")
public interface DeptService {

    /**
     * queryAll 分页
     * @param criteria
     * @param pageable
     * @return
     */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(DeptQueryCriteria criteria, Pageable pageable);

    /**
     * queryAll
     * @param criteria
     * @return
     */
    //@Cacheable(keyGenerator = "keyGenerator")
    List<DeptDTO> queryAll(DeptQueryCriteria criteria);

    List<Dept> query(DeptQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    DeptDTO findById(Long id);

    /**
     * findSubDeptById
     * @param deptId
     * @return
     */
    Page<Dept> findSubDeptById(Long deptId, Pageable pageable);

    /**
     * findSubDeptById，不分页
     * @param deptId
     * @return
     */
    List<Dept> findSubDeptById(Long deptId);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    DeptDTO create(Dept resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Dept resources);

    /**
     * delete
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Long id);

    /**
     * buildTree
     * @param deptDTOS
     * @return
     */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object buildTree(List<DeptDTO> deptDTOS);

    /**
     * findByPid
     * @param pid
     * @return
     */
    //@Cacheable(keyGenerator = "keyGenerator")
    List<Dept> findByPid(long pid);

    Set<Dept> findByRoleIds(Long id);

    void bindByCode(Long deptId, List<String> codeList);
}
