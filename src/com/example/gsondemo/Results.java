package com.example.gsondemo;

import java.util.List;

public class Results {

	private List<AddressComponents> address_components;
	private String formatted_address;
	private Geometry geometry;
	private List<String> types;
	
	public List<String> getTypes() {
		return types;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public List<AddressComponents> getAddress_components() {
		return address_components;
	}
}
