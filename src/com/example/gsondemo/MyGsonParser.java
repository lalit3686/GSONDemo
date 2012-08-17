package com.example.gsondemo;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.google.gson.Gson;

public class MyGsonParser {

	/******
	 * 
	 *Parses JSON data using Gson and fills the respective classes with response.
	 *
	 *@return returns the parent class with response. Returns null if some problem occurs while parsing. 
	 * 
	 ******/
    public Object getData(String url, List<NameValuePair> mNameValuePairs, Object mObject) {
		String responseBody = "";
		HttpParams mHttpParams;
		HttpPost mHttpPost = new HttpPost("http://mygogolfteetime.com/iphone/topfive/155");
		Gson gson = new Gson();
		try {
			HttpClient httpclient = new DefaultHttpClient();
			mHttpParams = httpclient.getParams();
			HttpConnectionParams.setConnectionTimeout(mHttpParams, 30000);
			mHttpPost.setHeader("Content-Type","application/json");
			mHttpPost.setEntity(new UrlEncodedFormEntity(mNameValuePairs));
			HttpResponse httpResponse = (BasicHttpResponse) httpclient.execute(mHttpPost);
			if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				responseBody = EntityUtils.toString(httpResponse.getEntity());
				Log.v("data", responseBody);
				if(responseBody.startsWith("[")){
					responseBody = "{\"result\":"+responseBody+"}";
				}
				mObject = gson.fromJson(responseBody, mObject.getClass());
			}
			else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return mObject; 
	}
}
