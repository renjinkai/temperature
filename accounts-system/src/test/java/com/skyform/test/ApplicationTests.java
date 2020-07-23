package com.skyform.test;

import com.skyform.modules.system.service.StudentService;
import com.skyform.modules.system.service.dto.StudentDTO;
import com.skyform.modules.system.service.dto.StudentQueryCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private StudentService studentService;

	@Test
	public void test(){
		StudentQueryCriteria criteria = new StudentQueryCriteria();
		List<StudentDTO> list = studentService.queryAll(criteria);
		System.out.println(list);
	}
}

