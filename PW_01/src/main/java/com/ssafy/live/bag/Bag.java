package com.ssafy.live.bag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Bag {
	private Hat hat;
	private Watch watch;
	private SmartWatch sWatch;
	
	@Autowired
	public Bag(Hat hat, @Qualifier("watch") Watch watch) {
		this.hat = hat;
		this.watch = watch;
	}
	
	public void setSmartWatch(SmartWatch sWatch) {
		this.sWatch = sWatch;
	}
}
