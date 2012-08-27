package com.example.gsondemo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.http.NameValuePair;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gsondemo.GeometryViewport.NorthEast;
import com.example.gsondemo.GeometryViewport.SouthWest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

// http://maps.googleapis.com/maps/api/geocode/json?address=23.23,72.72&sensor=false
public class MainActivity extends Activity {

	private TextView textResponse;
	private Context mContext;
	private String jsonObjectString = "{\"result\":[{\"name\" : \"lalit\"}, {\"name\" : \"jaimin\"}], \"someKey\": \"someValue\"}";
	private String jsonArrayString = "[{\"name\" : \"lalit\"}, {\"name\" : \"jaimin\"}]";
	private String url = "http://maps.googleapis.com/maps/api/geocode/json?address=22.99948365856307,72.60040283203125&sensor=false"; 
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        mContext = this;
	        
	        readStringGson();
	        readArrayGson();
	        getWebServiceData();
	    }
	 
	 private void getWebServiceData() {
		 textResponse = (TextView) findViewById(R.id.textResponse);

	        // create instance of parent Object/Class to be filled. 
	        DataObject mObject = new DataObject();
	        
	        // List out the parameters to be passed.
	        List<NameValuePair> mNameValuePairs = new ArrayList<NameValuePair>();
	        
	        // create instance of Parser class.
	        MyGsonParser mGsonParser = new MyGsonParser();
	        
	        if(CheckConnectivity()){
	        	mObject = (DataObject) mGsonParser.getData(url, mNameValuePairs, mObject);
	            if(mObject != null){
	            	Log.i("response", mObject.status);
	            	fetchData(mObject);
	            }
	            else{
	            	Toast.makeText(mContext, "Some Problem Occured", Toast.LENGTH_LONG).show();
	            }
	        }
	        else{
	        	Toast.makeText(mContext, "No Internet Connectivity", Toast.LENGTH_LONG).show();
	        }
	 }
	
	/***
	 * Checks Internet Connectivity.
	 * 
	 * @return returns true of connected else returns false.
	 * */
	 private boolean CheckConnectivity() {
	    	ConnectivityManager mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			if (mConnectivityManager.getActiveNetworkInfo() != null && mConnectivityManager.getActiveNetworkInfo().isAvailable() && mConnectivityManager.getActiveNetworkInfo().isConnected()) {
				return true;
			} else {
				return false;
			}
		}
	 
	 private void readStringGson() {
		 Gson gson = new Gson();
	        TestObject mTest = gson.fromJson(jsonObjectString, TestObject.class); 
	        ArrayList<TestResult> mResult = (ArrayList<TestResult>) mTest.result;
	        for(TestResult t : mResult){
	        	Log.d("Name", t.name);
	        }
	        Log.d("Name", mTest.someKey);
	}
	 
	private void readArrayGson() {
		
		Gson gson = new Gson();
		Type listType = new TypeToken<Collection<TestResult>>(){}.getType();
		@SuppressWarnings("unchecked")
		List<TestResult> posts = (List<TestResult>) gson.fromJson(jsonArrayString, listType);
		
		for(TestResult mResult : posts){
			System.out.println(mResult.name);
		}
	}
	
    /**
     * 
     * Fetches data from the Gson response and appends to TextView.
     * 
     * */
    private void fetchData(DataObject mObject) {
    	try {
				List<Results> results = mObject.results;
				for(Results result : results){
					
					textResponse.append("\n*** AddressComponents ***\n");
					List<AddressComponents> addressComponents = result.address_components;
					textResponse.append("\nSize - "+ addressComponents.size()+"");
					for(AddressComponents components : addressComponents){
						textResponse.append("\nlong_name - "+ components.long_name.toString());
						textResponse.append("\nshort_name - "+ components.short_name.toString());
						
						for(String type : components.types){
							textResponse.append("\ntypes - "+ type);							
						}
					}
					
					textResponse.append("\n\nformated_address - "+result.formatted_address);
					
					textResponse.append("\n\n*** Geometry ***\n");
					Geometry geometry = result.geometry;
					textResponse.append("\nLocation_type - "+geometry.location_type);
					
					textResponse.append("\n\n*** GeometryLocation ***\n");
					GeometryLocation geometryLocation = result.geometry.location;
					textResponse.append("\nLatitude - "+ geometryLocation.lat);
					textResponse.append("\nLongitude - "+ geometryLocation.lng);
					
					textResponse.append("\n\n*** GeometryViewport ***\n");
					GeometryViewport viewport = result.geometry.viewport;
					
					textResponse.append("\n\n*** NorthEast ***\n");
					NorthEast northeast = viewport.northeast;
					textResponse.append("\nLatitude - "+ northeast.lat);
					textResponse.append("\nLongitude - "+ northeast.lng);
					
					textResponse.append("\n\n*** SouthWest ***\n");
					SouthWest southwest = viewport.southwest;
					textResponse.append("\nLatitude - "+ southwest.lat);
					textResponse.append("\nLongitude - "+ southwest.lng);
					
					textResponse.append("\n\n*** Types ***\n");
					for(String type : result.types){
						textResponse.append("\ntype - "+ type);	
					}
					textResponse.append("\n\nstatus - "+ mObject.status);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
