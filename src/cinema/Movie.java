package cinema;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

public class Movie {

	private String title;
	private String desc;
	private String imgSrc;

public Movie (String title,String desc, String imgSrc)
{
	this.title=title;
	this.desc=desc;
	this.imgSrc=imgSrc;
	try {
		addMovie();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void addMovie() throws IOException
{
	
	JSONObject obj = JSONUtils.getJSONObjectFromFile("database.json");
	JSONArray jsonArray = obj.getJSONArray("Movies");
	JSONObject temp = new JSONObject();
	temp.put("title", title);
	temp.put("desc", desc);
	temp.put("imgSrc", imgSrc);
	
	jsonArray.put(temp);
	
	
	BufferedWriter writer= new BufferedWriter( new FileWriter("database.json"));
	writer.write(obj.toString());
	writer.close();
	
	}
}
