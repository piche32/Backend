package com.ssafy.live.washer;

public class WasherUser {
	private Washer washer; // has a - 의존성
	
	public WasherUser(Washer washer) {
		this.washer = washer;
	}
	
	public void setWasher(Washer washer) {
		this.washer = washer;
	}
	
	public Washer getWasher() {
		return this.washer;
	}
	
	public void useWasher(String clothes) {
		this.washer.wash(clothes);
	}
}
