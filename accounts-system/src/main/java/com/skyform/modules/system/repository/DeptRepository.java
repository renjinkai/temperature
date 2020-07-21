package com.skyform.modules.system.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.skyform.modules.system.domain.Dept;

import java.util.List;
import java.util.Set;

public interface DeptRepository extends JpaRepository<Dept, Long>, JpaSpecificationExecutor {

    /**
     * findByPid
     * @param id
     * @return
     */
    List<Dept> findByPid(Long id);

    @Query(value = "select name from dept where id = ?1",nativeQuery = true)
    String findNameById(Long id);

    @Query(value = "SELECT * FROM dept WHERE pid = (select id from dept where id = ?1)",nativeQuery = true)
    Page<Dept> findSubDeptById(Long deptId, Pageable pageable);

    Set<Dept> findByRoles_Id(Long id);
}