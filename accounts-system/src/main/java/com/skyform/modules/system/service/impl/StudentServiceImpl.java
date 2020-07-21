package com.skyform.modules.system.service.impl;

import com.skyform.exception.BadRequestException;
import com.skyform.modules.system.domain.Student;
import com.skyform.modules.system.repository.StudentRepository;
import com.skyform.modules.system.service.StudentService;
import com.skyform.modules.system.service.dto.StudentDTO;
import com.skyform.modules.system.service.dto.StudentQueryCriteria;
import com.skyform.modules.system.service.mapper.StudentMapper;
import com.skyform.modules.system.service.mybatis_mapper.StudentMybatisMapper;
import com.skyform.utils.PageUtil;
import com.skyform.utils.QueryHelp;
import com.skyform.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
* @author renjk
* @date 2020-05-29
*/
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentMybatisMapper studentMybatisMapper;

    @Override
    public Object queryAll(StudentQueryCriteria criteria, Pageable pageable){
        Page<Student> page = studentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page);
    }

    @Override
    public List<StudentDTO> queryAll(StudentQueryCriteria criteria){
        return studentMapper.toDto(studentRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public StudentDTO findById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        ValidationUtil.isNull(student,"Student","id",id);
        return studentMapper.toDto(student.get());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public StudentDTO create(Student resources) {
        return studentMapper.toDto(studentRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Student resources) {
        Optional<Student> optionalStudent = studentRepository.findById(resources.getId());
        ValidationUtil.isNull( optionalStudent,"Student","id",resources.getId());
        Student student = optionalStudent.get();
        student.copy(resources);
        studentRepository.save(student);
    }

    @Override
    public void updateBatch(List<Student> resources) {
        if(resources.size() > 0){
            for(Student student : resources){
                this.update(student);
            }
        }else{
            throw new BadRequestException("学生信息列表为空");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void updateByIdCard(Student student) {
        studentMybatisMapper.updateByIdCard(student);
    }
}
