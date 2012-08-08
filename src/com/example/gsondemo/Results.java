package com.example.gsondemo;

import java.util.ArrayList;
import java.util.List;

public class Results {

	private List<AddressComponents> address_components = new ArrayList<AddressComponents>();
	private String formatted_address;
	private Geometry geometry;
	private List<String> types = new ArrayList<String>();
	
	public List<String> getTypes() {
		return types;
	}

	public void setTypes(List<String> types) {
		this.types = types;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	public List<AddressComponents> getAddress_components() {
		return address_components;
	}

	public void setAddress_components(List<AddressComponents> address_components) {
		this.address_components = address_components;
	}
}
