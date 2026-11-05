package com.ssafy.live.washer.config;

import com.ssafy.live.washer.Washer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ssafy.live.washer.LWasher;
import com.ssafy.live.washer.SWasher;
import com.ssafy.live.washer.WasherUser;

@Configuration
public class WasherConfig {

	@Bean
	public SWasher sWasher() {
		return new SWasher();
	}
	@Bean
	public LWasher lWasher() {
		return new LWasher();
	}
	@Bean("myWasherUser")
	public WasherUser WasherUser() {
		Washer washer = sWasher();
		return new WasherUser(washer);
	}
}
