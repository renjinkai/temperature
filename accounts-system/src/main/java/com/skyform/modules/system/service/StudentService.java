package com.skyform.modules.system.service;

import com.skyform.modules.system.domain.Student;
import com.skyform.modules.system.service.dto.StudentDTO;
import com.skyform.modules.system.service.dto.StudentQueryCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author renjk
* @date 2020-05-29
*/
//@CacheConfig(cacheNames = "student")
public interface StudentService {

    /**
    * queryAll 分页
    * @param criteria
    * @param pageable
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    Object queryAll(StudentQueryCriteria criteria, Pageable pageable);

    /**
    * queryAll 不分页
    * @param criteria
    * @return
    */
    //@Cacheable(keyGenerator = "keyGenerator")
    List<StudentDTO> queryAll(StudentQueryCriteria criteria);

    List<StudentDTO> queryAllExcel(StudentQueryCriteria criteria);

    /**
     * findById
     * @param id
     * @return
     */
    //@Cacheable(key = "#p0")
    StudentDTO findById(Long id);

    /**
     * create
     * @param resources
     * @return
     */
    //@CacheEvict(allEntries = true)
    StudentDTO create(Student resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void update(Student resources);

    /**
     * update
     * @param resources
     */
    //@CacheEvict(allEntries = true)
    void updateBatch(List<Student> resources);

    /**
     * delete
     * @param id
     */
    //@CacheEvict(allEntries = true)
    void delete(Long id);

    /**
     * updateByIdCard
     * @param student
     */
    void updateByIdCard(Student student);
}
