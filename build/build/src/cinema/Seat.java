package cinema;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

public class Seat {

	private boolean booked;
	private int index;
	private int i;
	private int j;
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
public void book()
{
	booked=true;
}
public boolean getBooked()
{
	return booked;
}
public void addSeat() throws IOException
{
	
	JSONObject obj = JSONUtils.getJSONObjectFromFile("./src/assets/obj.json");
	JSONArray jsonArray = obj.getJSONArray("Screenings");
	JSONObject currentScreening = jsonArray.getJSONObject(index);
	JSONArray seat = currentScreening.getJSONArray("seats");
	JSONObject temp = new JSONObject();
	temp.put("coor", i+","+j);
	temp.put("booked", booked);
	
	
	seat.put(temp);
	
	
	BufferedWriter writer= new BufferedWriter( new FileWriter("./src/assets/obj.json"));
	writer.write(obj.toString());
	writer.close();
	
	}
}

