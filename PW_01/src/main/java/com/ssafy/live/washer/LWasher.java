package com.ssafy.live.washer;

public class LWasher implements Washer{

	@Override
	public void wash(String cloths) {
		System.out.println("옷을 세탁하고 있어요~~~~~~" + this.getClass().getName());
	}
}
