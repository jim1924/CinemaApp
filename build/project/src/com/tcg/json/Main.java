package com.tcg.json;

import java.io.BufferedWriter;
import java.io.FileWriter;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

	public static void main(String[] args) throws Exception {
		JSONObject obj = JSONUtils.getJSONObjectFromFile("/assets/obj.json");
		JSONArray jsonArray = obj.getJSONArray("LoginDetails");
		System.out.println(jsonArray.getJSONObject(1).getString("username"));
		jsonArray.getJSONObject(1).put("username", "AFIFY");
		System.out.println(jsonArray.getJSONObject(1).getString("username"));
		
		JSONObject temp = new JSONObject();
		//temp.put("username", "skata");
		
		
		
		//jsonArray.put(  ,temp);// puts  the object to the index 2
		//jsonArray.remove(2);//removes the index 2
		BufferedWriter writer= new BufferedWriter( new FileWriter("./src/assets/obj.json"));
		writer.write(obj.toString());
		writer.close();
		
		
		//BufferedWriter writer = new BufferedWriter(new FileWriter("./src/assets/skata.txt"));
		//FileWriter filew=new FileWriter("src/assets/obj.json");
		
		
/*		String[] names = JSONObject.getNames(obj); //gets only the category(names)
		for(String string : names) {
		System.out.println(string + ": " + obj.get(string));//gets the content of each name
		}
		
		System.out.print("\n");
		
		JSONArray jsonArray = obj.getJSONArray("Array"); //searches for an array called Array
		for(int i = 0; i < jsonArray.length(); i++) {
			System.out.println(jsonArray.get(i));
		}
		
		System.out.print("\n");
	
		
		JSONArray jsonArray2 = obj.getJSONArray("skata");
		for(int i = 0; i < jsonArray2.length(); i++) {
			System.out.println(jsonArray2.get(i));
		}
		
		
		System.out.print("\n");
		
		
		int number2 = obj.getInt("Number2"); //searches for the name Number2 and return the Integer
		System.out.println(number2);
		
		System.out.print("\n");
		
		
		int number = obj.getInt("Number"); //searches for the name Number and return the Integer
		System.out.println(number);
		
		System.out.print("\n");
		
		String string = obj.getString("String");// finds the String name and returns the  String it has inside
		System.out.println(string);
		
		System.out.print("\n");
		
		boolean bool = obj.getBoolean("Boolean");
		System.out.println(bool);*/
		
	}

}
