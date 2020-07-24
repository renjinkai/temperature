package com.skyform.test;

import com.skyform.modules.system.domain.DeviceMessage;
import com.skyform.modules.system.domain.Temperature;
import com.skyform.modules.system.service.TemperatureService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private TemperatureService temperatureService;

	@Test
	public void test(){
		DeviceMessage resources = new DeviceMessage();
		resources.setMessageType("dataReport");
		resources.setDeviceId("5e0ea06716eb4469b33206d5ef682ee3");
		resources.setPayLoad("{APPdata=AQCLaAEAi9ABAIvFAQCLWQEAi2gBAIvQAQCLxQEAi1kBAItoAQCL0AEAi8UBAItZAQCLaAEAi9ABAIvFAQCLWQEAi2gBAIvQAQCLxQEAi1kBAItoAQCL0AEAi8UBAItZ}");
		resources.setDeviceTime(new Timestamp(new Date().getTime()));
		List<Temperature> list = temperatureService.analysisData(resources);
		//录入温度表
		if(list.size()>0){
			for (Temperature temperature:list){
				temperatureService.create(temperature);
			}
		}
	}
}

