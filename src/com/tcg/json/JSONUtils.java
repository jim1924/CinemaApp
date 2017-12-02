package com.tcg.json;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONObject;

public class JSONUtils {

	public static String getJSONStringFromFile(String jsonFile) throws IOException {
		
		StringBuilder stringBuilder= new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(jsonFile)));
		try{
			String line;
			while ((line=in.readLine())!=null){
				stringBuilder.append(line);
			}
		}
		finally{
			in.close();
		}
		return stringBuilder.toString();

	}
	
	public static JSONObject getJSONObjectFromFile(String jsonFile) throws IOException {
		return new JSONObject(getJSONStringFromFile(jsonFile));
	}
	
	public static boolean objectExists(JSONObject jsonObject, String key) {
		Object o;
		try {
			o = jsonObject.get(key);
		} catch(Exception e) {
			return false;
		}
		return o != null;
	}
	
}