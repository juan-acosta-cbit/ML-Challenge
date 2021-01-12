package com.starwars.quasar.objects;

import java.util.List;

public class MessageSplitRequest {

	private Double       distance;
	private List<String> message;
	
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public List<String> getMessage() {
		return message;
	}
	public void setMessage(List<String> message) {
		this.message = message;
	}
	
	

}