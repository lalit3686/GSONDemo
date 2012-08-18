package com.example.gsondemo;


public class Geometry {

	private String location_type;
	private GeometryLocation location;
	private GeometryViewport viewport;

	public GeometryViewport getViewport() {
		return viewport;
	}

	public void setViewport(GeometryViewport viewport) {
		this.viewport = viewport;
	}

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
