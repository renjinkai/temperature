package com.skyform.test;

import com.skyform.modules.system.domain.Dept;
import com.skyform.modules.system.repository.TemperatureRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private TemperatureRepository temperatureRepository;

	@Test
	public void test(){
		List<Long> subDeptIds = new ArrayList<>();
		subDeptIds.add(31L);
		subDeptIds.add(32L);
		System.out.println(temperatureRepository.findDeviceIdByDeptIds(subDeptIds));
	}
}

