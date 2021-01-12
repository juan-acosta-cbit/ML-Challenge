package com.starwars.quasar.objects;

import java.util.List;


public class MessageRequest {

	private List<MessageSatelliteRequest> satellites;

	public List<MessageSatelliteRequest> getSatellites() {
		return satellites;
	}

	public void setSatellites(List<MessageSatelliteRequest> satellites) {
		this.satellites = satellites;
	}

	

	
}