package cinema;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class Movie.
 * @author Ahmed Afify
 */
public class Movie {

	/** The title. */
	private String title;
	
	/** The desc. */
	private String desc;
	
	/** The img src. */
	private String imgSrc;

/**
 * Instantiates a new movie.
 *
 * @param title the title
 * @param desc the desc
 * @param imgSrc the img src
 */
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

/**
 * Adds the movie.
 *
 * @throws IOException Signals that an I/O exception has occurred.
 */
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
