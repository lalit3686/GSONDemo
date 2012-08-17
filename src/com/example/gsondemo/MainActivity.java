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

import com.example.gsondemo.TestJSONArray.ListData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

// http://maps.googleapis.com/maps/api/geocode/json?address=23.23,72.72&sensor=false
public class MainActivity extends Activity {

	TextView textResponse;
	Context mContext;
	String json = "{\"result\":[{\"name\" : \"lalit\"}, {\"name\" : \"jaimin\"}], \"someKey\": \"someValue\"}";
	String jsonArray = "[{\"name\" : \"lalit\"}, {\"name\" : \"jaimin\"}]";
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
	        TestObject mTest = gson.fromJson(json, TestObject.class); 
	        ArrayList<TestResult> mResult = (ArrayList<TestResult>) mTest.getResult();
	        for(TestResult t : mResult){
	        	Log.d("Name", t.getName());
	        }
	        Log.d("Name", mTest.getSomeKey());
	}
	 
	private void getJSONArrayData() {
		 MyGsonParser mGsonParser = new MyGsonParser();
		 List<NameValuePair> mNameValuePairs = new ArrayList<NameValuePair>();
		 
		 TestJSONArray mTestJSONArray = new TestJSONArray();
		 mTestJSONArray = (TestJSONArray) mGsonParser.getData("http://192.168.0.39/komal/karma/webservice/jsonresponse.php?action=view_connections&userid=148", mNameValuePairs, mTestJSONArray);
		 
		 if(mTestJSONArray != null){
			 System.out.println(mTestJSONArray.toString());
			 List<ListData> mDatas = mTestJSONArray.getResult();
			 for(ListData mData : mDatas){
				 System.out.println(mData.getUserid());	 
			 }	 
		 }
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
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        
        readStringGson();
        readArrayGson();
        getJSONArrayData();
        
        textResponse = (TextView) findViewById(R.id.textResponse);

        // create instance of parent Object/Class to be filled. 
        DataObject mObject = new DataObject();
        
        // List out the parameters to be passed.
        List<NameValuePair> mNameValuePairs = new ArrayList<NameValuePair>();
        
        // create instance of Parser class.
        MyGsonParser mGsonParser = new MyGsonParser();
        
        if(CheckConnectivity()){
        	mObject = (DataObject) mGsonParser.getData("http://maps.googleapis.com/maps/api/geocode/json?address=22.99948365856307,72.60040283203125&sensor=false", mNameValuePairs, mObject);
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
    
    /**
     * 
     * Fetches data from the Gson response and appends to TextView.
     * 
     * */
    private void fetchData(DataObject mObject) {
    	try {
        	textResponse.append("\nresponse - "+ mObject.getStatus());
				List<Results> results = mObject.getResults();
				for(Results res : results){
					
					textResponse.append("\n*** AddressComponents ***");
					List<AddressComponents> addressComponents = res.getAddress_components();
					textResponse.append("\nSize - "+ addressComponents.size()+"");
					for(AddressComponents components : addressComponents){
						textResponse.append("\nlong_name - "+ components.getLong_name().toString());
						textResponse.append("\nshort_name - "+ components.getShort_name().toString());
						
						for(String type : components.getTypes()){
							textResponse.append("\ntypes - "+ type);							
						}
					}
					
					textResponse.append("\ntype - "+ res.getTypes().toString());
					textResponse.append("\nformated_address - "+res.getFormatted_address());
					
					textResponse.append("\n*** Geometry ***");
					Geometry geometry = res.getGeometry();
					textResponse.append("\nLocation_type - "+geometry.getLocation_type());
					
					textResponse.append("\n*** GeometryLocation ***");
					GeometryLocation geometryLocation = res.getGeometry().getLocation();
					textResponse.append("\nLatitude - "+ geometryLocation.getLat());
					textResponse.append("\nLongitude - "+ geometryLocation.getLng());
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
