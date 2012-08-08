package com.example.gsondemo;

import java.util.ArrayList;
import java.util.List;

public class DataObject {

	private String status;
	private List<Results> results = new ArrayList<Results>();

	public List<Results> getResults() {
		return results;
	}

	public void setResults(List<Results> results) {
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
