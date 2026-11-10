package com.ssafy.live;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest //통합테스트를 위한 annotation
class Fw03ApplicationTests {
	
	private final static Logger log = LoggerFactory.getLogger(Fw03ApplicationTests.class);
	@Value("${manager.id}")
	private String id;
	@Value("${manager.age}")
	private String age;
	@Value("${manager.roles}")
	private List<String> roles;
	@Test
	void contextLoads() {
		Assertions.assertEquals("hong", id);
		Assertions.assertEquals("30", age);
		
		Assertions.assertEquals(List.of("admin", "manager", "guest"), roles);
		
		log.debug("roles: {}", roles);
	}

}
