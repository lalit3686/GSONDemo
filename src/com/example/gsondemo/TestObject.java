package com.example.gsondemo;

import java.util.ArrayList;
import java.util.List;

public class TestObject {

	private List<TestResult> result = new ArrayList<TestResult>();
	private String someKey;

	public String getSomeKey() {
		return someKey;
	}

	public void setSomeKey(String someKey) {
		this.someKey = someKey;
	}

	public List<TestResult> getResult() {
		return result;
	}

	public void setResult(List<TestResult> result) {
		this.result = result;
	}
}
