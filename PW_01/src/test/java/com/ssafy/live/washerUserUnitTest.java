package com.ssafy.live;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ssafy.live.washer.SWasher;
import com.ssafy.live.washer.Washer;
import com.ssafy.live.washer.WasherUser;
import com.ssafy.live.washer.config.WasherConfig;

public class washerUserUnitTest {
	ApplicationContext ctx;
	
	@BeforeEach
	public void setup() {
		ctx = new AnnotationConfigApplicationContext(WasherConfig.class);
	}
	
	@Test
	public void beanTest() {
		SWasher sWasher = ctx.getBean(SWasher.class);
		Assertions.assertNotNull(sWasher);
	}
	

	@Test
	public void beanTest2() {
		Assertions.assertThrows(NoUniqueBeanDefinitionException.class, ()->{
			ctx.getBean(Washer.class);
		});
	}
	
	@Test
	public void singletonTest() {
		SWasher sWasher = ctx.getBean(SWasher.class);
		WasherUser user = ctx.getBean(WasherUser.class);
		
		Assertions.assertSame(sWasher, user.getWasher());
	}
}
