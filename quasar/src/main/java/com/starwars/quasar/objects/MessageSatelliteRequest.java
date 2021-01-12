package com.starwars.quasar.objects;

import java.util.List;

public class MessageSatelliteRequest {

	private String       name;
	private Double       distance;
	private List<String> message;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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