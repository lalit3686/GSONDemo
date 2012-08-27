package com.example.gsondemo;


public class Geometry {

	private String location_type;
	private GeometryLocation location;
	private GeometryViewport viewport;

	public GeometryViewport getViewport() {
		return viewport;
	}

	public GeometryLocation getLocation() {
		return location;
	}

	public String getLocation_type() {
		return location_type;
	}
}
