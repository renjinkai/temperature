package com.skyform.modules.system.service.mybatis_mapper;

import com.skyform.modules.system.domain.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMybatisMapper {

    void updateByIdCard(Student student);

}
