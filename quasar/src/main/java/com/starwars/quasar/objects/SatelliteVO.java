package com.starwars.quasar.objects;

public class SatelliteVO {

	private PositionVO location;
	private String name;
	
	public SatelliteVO(PositionVO location, String name) {
		super();
		this.location = location;
		this.name = name;
	}

	public PositionVO getLocation() {
		return location;
	}

	public void setLocation(PositionVO location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}