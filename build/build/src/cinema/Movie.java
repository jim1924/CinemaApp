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

public Movie (String title,String desc)
{
	this.title=title;
	this.desc=desc;
	this.imgSrc="/assets/"+title+".jpg";
	try {
		addMovie();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void addMovie() throws IOException
{
	
	JSONObject obj = JSONUtils.getJSONObjectFromFile("./src/assets/obj.json");
	JSONArray jsonArray = obj.getJSONArray("Movies");
	JSONObject temp = new JSONObject();
	temp.put("title", title);
	temp.put("desc", desc);
	temp.put("imgSrc", imgSrc);
	
	jsonArray.put(temp);
	
	
	BufferedWriter writer= new BufferedWriter( new FileWriter("./src/assets/obj.json"));
	writer.write(obj.toString());
	writer.close();
	
	}
}
