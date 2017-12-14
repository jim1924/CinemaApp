package cinema;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class Screening.
 * @author Ahmed Afify
 */
public class Screening {

/** The date. */
private String date;

/** The time. */
private String time;

/** The title. */
private String title;

/** The index. */
public int index;

/** The theater. */
private Seat[][] theater = new Seat[10][10];

/**
 * Instantiates a new screening.
 *
 * @param date the date
 * @param time the time
 * @param title the title
 */
public Screening(String date, String time, String title)
{
	this.date=date;
	this.time=time;
	this.title=title;
	
	
	try {
		addScreening();
		for (int i=0; i<10; i++)
		{
			for (int j=0; j<10; j++)
			{
				theater[i][j]=new Seat(index,i,j);
			}
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

/**
 * Gets the seat.
 *
 * @param x the x
 * @param y the y
 * @return the seat
 */
public Seat getSeat(int x,int y)
{
	return theater[x][y];
	
}

/**
 * Adds the screening.
 *
 * @throws IOException Signals that an I/O exception has occurred.
 */
public void addScreening() throws IOException
{
	
	JSONObject obj = JSONUtils.getJSONObjectFromFile("database.json");
	JSONArray jsonArray = obj.getJSONArray("Screenings");
	index = jsonArray.length();
	JSONObject temp = new JSONObject();
	JSONArray seats = new JSONArray();
	temp.put("date", date);
	temp.put("time", time);
	temp.put("title", title);
	temp.put("screeningID", index+1);
	temp.put("seats", seats);
	temp.put("bookedSeats", 0);
	temp.put("freeSeats", 100);
	
	jsonArray.put(temp);
	
	
	BufferedWriter writer= new BufferedWriter( new FileWriter("database.json"));
	writer.write(obj.toString());
	writer.close();
	
	}
}
