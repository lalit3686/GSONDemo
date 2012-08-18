package com.example.gsondemo;

public class GeometryViewport {

	private NorthEast northeast;
	private SouthWest southwest;
	
	public NorthEast getmNorthEast() {
		return northeast;
	}

	public void setmNorthEast(NorthEast northeast) {
		this.northeast = northeast;
	}

	public SouthWest getmSouthWest() {
		return southwest;
	}

	public void setmSouthWest(SouthWest southwest) {
		this.southwest = southwest;
	}

	class NorthEast{

		private String lat;
		private String lng;

		public String getLng() {
			return lng;
		}

		public void setLng(String lng) {
			this.lng = lng;
		}

		public String getLat() {
			return lat;
		}

		public void setLat(String lat) {
			this.lat = lat;
		}

	}
	
	class SouthWest{

		private String lat;
		private String lng;

		public String getLng() {
			return lng;
		}

		public void setLng(String lng) {
			this.lng = lng;
		}

		public String getLat() {
			return lat;
		}

		public void setLat(String lat) {
			this.lat = lat;
		}

	}
}
