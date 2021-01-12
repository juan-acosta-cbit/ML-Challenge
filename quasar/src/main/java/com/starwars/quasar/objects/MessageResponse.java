package com.starwars.quasar.objects;

public class MessageResponse {
	
	private PositionVO position;
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public PositionVO getPosition() {
		return position;
	}
	public void setPosition(PositionVO position) {
		this.position = position;
	}
	
}