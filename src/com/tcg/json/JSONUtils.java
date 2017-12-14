package com.tcg.json;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONObject;

/**
 * This class is used everytime a JSON file needs to be read.
 * @author Dimitris Selalmazidis
 *
 */
public class JSONUtils {

	/**
	 * This method is responsible for reading the JSON database.
	 * @param jsonFile
	 * @return
	 * @throws IOException
	 */
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
	
	/**
	 * This method is responsible for reading the JSON database.
	 * @param jsonFile
	 * @return
	 * @throws IOException
	 */
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
	
	/**
	 * This method is just an overoload of the previous method
	 * @param jsonFile
	 * @return
	 * @throws IOException
	 */
	public static String getJSONStringFromFile(InputStream jsonFile) throws IOException {
		StringBuilder stringBuilder= new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(jsonFile));
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
	
	/**
	 * This method is just an overoload of the previous method
	 * @param jsonFile
	 * @return
	 * @throws IOException
	 */
	public static JSONObject getJSONObjectFromFile(InputStream jsonFile) throws IOException {
		return new JSONObject(getJSONStringFromFile(jsonFile));
	}
	

	
	
	
	
	
}