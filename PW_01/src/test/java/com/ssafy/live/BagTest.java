package com.ssafy.live;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ssafy.live.bag.Hat;
import com.ssafy.live.bag.config.BagConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BagConfig.class)
public class BagTest {
	
	@Autowired
	Hat hat;
	
	@Test
	public void beanTest() {
		Assertions.assertNotNull(hat);
	}
}

