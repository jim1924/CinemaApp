package com.tcg.json;

import org.json.*;

public class Main {

	public static void main(String[] args) {
		JSONObject obj = JSONUtils.getJSONObjectFromFile("/assets/obj.json");
		String[] names = JSONObject.getNames(obj); //gets only the category(names)
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
		System.out.println(bool);
		
	}

}
