package cinema;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class Seat.
 * @author Ahmed Afify
 */
public class Seat {

	/** The booked. */
	private boolean booked;
	
	/** The index. */
	private int index;
	
	/** The i. */
	private int i;
	
	/** The j. */
	private int j;

/**
 * Instantiates a new seat.
 *
 * @param index the index
 * @param i the i
 * @param j the j
 */
public Seat(int index, int i, int j) {
	this.booked=false;
	this.index = index;
	this.i=i;
	this.j=j;
	try {
		addSeat();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

/**
 * Book.
 */
public void book()
{
	booked=true;
}

/**
 * Gets the booked.
 *
 * @return the booked
 */
public boolean getBooked()
{
	return booked;
}

/**
 * Adds the seat.
 *
 * @throws IOException Signals that an I/O exception has occurred.
 */
public void addSeat() throws IOException
{
	
	JSONObject obj = JSONUtils.getJSONObjectFromFile("database.json");
	JSONArray jsonArray = obj.getJSONArray("Screenings");
	JSONObject currentScreening = jsonArray.getJSONObject(index);
	JSONArray seat = currentScreening.getJSONArray("seats");
	JSONObject temp = new JSONObject();
	temp.put("coor", i+","+j);
	temp.put("booked", booked);
	
	
	seat.put(temp);
	
	
	BufferedWriter writer= new BufferedWriter( new FileWriter("database.json"));
	writer.write(obj.toString());
	writer.close();
	
	}
}

