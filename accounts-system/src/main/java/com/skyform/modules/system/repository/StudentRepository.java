package com.skyform.modules.system.repository;

import com.skyform.modules.system.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
* @author renjk
* @date 2020-05-29
*/
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor {
}
