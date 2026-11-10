package com.ssafy.live;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.live.model.dto.Member;
import com.ssafy.live.model.service.BasicMemberService;

@SpringBootTest
public class BeanTest {

	@Autowired
	BasicMemberService service;
	
	@Test
	public void beanTest() {
		Assertions.assertNotNull(service);
	}
	
	@Test
	public void loginTest() throws SQLException {
		String email = "admin@ssafy.com";
		String password = "1234";
		
		Member member = service.login(email, password);
		
		Assertions.assertEquals("관리자", member.getName());
	}
}
