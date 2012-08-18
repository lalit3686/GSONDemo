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
	private String jsonObject = "{\"result\":[{\"name\" : \"lalit\"}, {\"name\" : \"jaimin\"}], \"someKey\": \"someValue\"}";
	private String jsonArray = "[{\"name\" : \"lalit\"}, {\"name\" : \"jaimin\"}]";
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
	            	Log.i("response", mObject.getStatus());
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
	        TestObject mTest = gson.fromJson(jsonObject, TestObject.class); 
	        ArrayList<TestResult> mResult = (ArrayList<TestResult>) mTest.getResult();
	        for(TestResult t : mResult){
	        	Log.d("Name", t.getName());
	        }
	        Log.d("Name", mTest.getSomeKey());
	}
	 
	private void readArrayGson() {
		
		Gson gson = new Gson();
		Type listType = new TypeToken<Collection<TestResult>>(){}.getType();
		@SuppressWarnings("unchecked")
		List<TestResult> posts = (List<TestResult>) gson.fromJson(jsonArray, listType);
		
		for(TestResult mResult : posts){
			System.out.println(mResult.getName());
		}
	}
	
    /**
     * 
     * Fetches data from the Gson response and appends to TextView.
     * 
     * */
    private void fetchData(DataObject mObject) {
    	try {
				List<Results> results = mObject.getResults();
				for(Results result : results){
					
					textResponse.append("\n*** AddressComponents ***\n");
					List<AddressComponents> addressComponents = result.getAddress_components();
					textResponse.append("\nSize - "+ addressComponents.size()+"");
					for(AddressComponents components : addressComponents){
						textResponse.append("\nlong_name - "+ components.getLong_name().toString());
						textResponse.append("\nshort_name - "+ components.getShort_name().toString());
						
						for(String type : components.getTypes()){
							textResponse.append("\ntypes - "+ type);							
						}
					}
					
					textResponse.append("\n\nformated_address - "+result.getFormatted_address());
					
					textResponse.append("\n\n*** Geometry ***\n");
					Geometry geometry = result.getGeometry();
					textResponse.append("\nLocation_type - "+geometry.getLocation_type());
					
					textResponse.append("\n\n*** GeometryLocation ***\n");
					GeometryLocation geometryLocation = result.getGeometry().getLocation();
					textResponse.append("\nLatitude - "+ geometryLocation.getLat());
					textResponse.append("\nLongitude - "+ geometryLocation.getLng());
					
					textResponse.append("\n\n*** GeometryViewport ***\n");
					GeometryViewport viewport = result.getGeometry().getViewport();
					
					textResponse.append("\n\n*** NorthEast ***\n");
					NorthEast northeast = viewport.getmNorthEast();
					textResponse.append("\nLatitude - "+ northeast.getLat());
					textResponse.append("\nLongitude - "+ northeast.getLng());
					
					textResponse.append("\n\n*** SouthWest ***\n");
					SouthWest southwest = viewport.getmSouthWest();
					textResponse.append("\nLatitude - "+ southwest.getLat());
					textResponse.append("\nLongitude - "+ southwest.getLng());
					
					textResponse.append("\n\n*** Types ***\n");
					for(String type : result.getTypes()){
						textResponse.append("\ntype - "+ type);	
					}
					textResponse.append("\n\nstatus - "+ mObject.getStatus());
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
