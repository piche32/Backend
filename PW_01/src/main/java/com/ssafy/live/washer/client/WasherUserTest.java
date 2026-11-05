package com.ssafy.live.washer.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ssafy.live.washer.SWasher;
import com.ssafy.live.washer.Washer;
import com.ssafy.live.washer.WasherUser;
import com.ssafy.live.washer.config.WasherConfig;

public class WasherUserTest {
public static void main(String[] args) {
	//spring에 설정전달.
	ApplicationContext ctx = new AnnotationConfigApplicationContext(WasherConfig.class);
	
	SWasher sWasher = ctx.getBean(SWasher.class);
	WasherUser user = ctx.getBean(WasherUser.class);
	user.useWasher("바지");
	
	System.out.println(sWasher == user.getWasher());
	
	Washer lwasher = ctx.getBean("lWasher", Washer.class);
	System.out.println(lwasher);
}
}
