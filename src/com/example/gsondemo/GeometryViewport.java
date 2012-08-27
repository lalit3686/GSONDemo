package com.example.gsondemo;

public class GeometryViewport {

	private NorthEast northeast;
	private SouthWest southwest;
	
	public NorthEast getmNorthEast() {
		return northeast;
	}

	public SouthWest getmSouthWest() {
		return southwest;
	}

	class NorthEast{

		private String lat;
		private String lng;

		public String getLng() {
			return lng;
		}

		public String getLat() {
			return lat;
		}
	}
	
	class SouthWest{

		private String lat;
		private String lng;

		public String getLng() {
			return lng;
		}

		public String getLat() {
			return lat;
		}
	}
}
