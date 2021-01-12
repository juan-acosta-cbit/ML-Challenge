package com.starwars.quasar.objects;

public class PositionVO {

	private Double x;
	private Double y;
	
	public PositionVO(Double x, Double y) {
		super();
		this.x = x;
		this.y = y;
	}	
	
	public PositionVO() {
		super();
	}

	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
}