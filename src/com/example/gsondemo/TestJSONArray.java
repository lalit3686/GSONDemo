package com.example.gsondemo;

import java.util.List;

public class TestJSONArray {

	List<ListData> result;
	
	public List<ListData> getResult() {
		return result;
	}

	public void setResult(List<ListData> result) {
		this.result = result;
	}

	class ListData{
		private String userid;

		public String getUserid() {
			return userid;
		}

		public void setUserid(String userid) {
			this.userid = userid;
		}
	}
}
