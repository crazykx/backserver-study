package com.kx.mq_leaning;

import com.kx.mq_leaning.producer.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MqLeaningApplicationTests {

	@Autowired
	private Sender sender;

	@Test
	public void contextLoads() {
		sender.send();
	}

}
