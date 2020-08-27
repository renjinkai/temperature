package com.skyform.test;

import com.skyform.modules.system.domain.User;
import com.skyform.modules.system.service.AppGroupService;
import com.skyform.modules.system.service.UserService;
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
	private AppGroupService appGroupService;

	@Autowired
	private UserService userService;

	@Test
	public void test(){
		//List<User> list = appGroupService.getAppGroupUsers(5);
		//System.out.println(list);

		List<User> list2 = userService.findByDeptId(2l);
		System.out.println(list2);
	}
}

