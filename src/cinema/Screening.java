package cinema;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

public class Screening {
private String date;
private String time;
private String title;
private Seat[][] theater = new Seat[10][10];

public Screening(String date, String time, String title)
{
	this.date=date;
	this.time=time;
	this.title=title;
	
	for (int i=0; i<10; i++)
	{
		for (int j=0; j<10; j++)
		{
			theater[i][j]=new Seat();
		}
	}
	try {
		addScreening();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public Seat getSeat(int x,int y)
{
	return theater[x][y];
	
}
public void addScreening() throws IOException
{
	
	JSONObject obj = JSONUtils.getJSONObjectFromFile("/assets/obj.json");
	JSONArray jsonArray = obj.getJSONArray("Screenings");
	JSONObject temp = new JSONObject();
	temp.put("date", date);
	temp.put("time", time);
	temp.put("title", title);
	
	jsonArray.put(temp);
	
	
	BufferedWriter writer= new BufferedWriter( new FileWriter("./src/assets/obj.json"));
	writer.write(obj.toString());
	writer.close();
	
	}
}
