package com.example.gsondemo;


public class Geometry {

	private String location_type;
	private GeometryLocation location;

	public GeometryLocation getLocation() {
		return location;
	}

	public void setLocation(GeometryLocation location) {
		this.location = location;
	}

	public String getLocation_type() {
		return location_type;
	}

	public void setLocation_type(String location_type) {
		this.location_type = location_type;
	}
}
