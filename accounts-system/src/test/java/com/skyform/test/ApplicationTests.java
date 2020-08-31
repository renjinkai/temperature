package com.skyform.test;

import com.skyform.modules.system.domain.User;
import com.skyform.modules.system.service.AppGroupService;
import com.skyform.modules.system.service.AppService;
import com.skyform.modules.system.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private AppService appService;

	@Test
	public void test(){
		Map<String, Object> map =  appService.getDevice(3);
		System.out.println(map);
	}
}

