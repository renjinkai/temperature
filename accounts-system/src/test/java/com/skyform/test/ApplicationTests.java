package com.skyform.test;

import com.skyform.modules.system.domain.Dept;
import com.skyform.modules.system.domain.Device;
import com.skyform.modules.system.domain.Student;
import com.skyform.modules.system.service.DeptService;
import com.skyform.modules.system.service.DeviceService;
import com.skyform.modules.system.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private DeviceService deviceService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private DeptService deptService;

	@Test
	public void device(){
		Device device = new Device();
		device.setDeviceId("D001");
		device.setName("张三");
		device.setBindTime(new Timestamp(new Date().getTime()));
		deviceService.updateByDeviceId(device);
	}

	@Test
	public void bind(){
		String deviceId = "D001";
		String idCard = "411521200005103820";
		String name = "张三";
		deviceService.bind(deviceId, idCard, name);
	}

	@Test
	public void unbind(){
		String deviceId = "D001";
		String idCard = "411521200005103820";
		deviceService.unbind(deviceId, idCard);
	}

	@Test
	public void deptCreate(){
		Dept dept = new Dept();
		dept.setName("Test");
		dept.setEnabled(true);
		deptService.create(dept);
	}
}

