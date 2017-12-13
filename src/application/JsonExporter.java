package application;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;


public class JsonExporter {
	public String status="";
    public JsonExporter(){
    JSONObject obj= new JSONObject();
    		try{
				obj = JSONUtils.getJSONObjectFromFile("database.json");
			}
			catch(Exception e){
				System.out.println(e);
				status="Export Failed";
			}
    	JSONArray screenings = obj.getJSONArray("Screenings");
    
    try {
        File file=new File("assets/Summary.csv");
        String csv = CDL.toString(screenings);
        FileUtils.writeStringToFile(file, csv);
        status="Export Succesful";
    } catch (JSONException e) {
        e.printStackTrace();
        status="Export Failed";
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        status="Export Failed";
    }           
    }
    public String getStatus()
    {
    	return status;
    }

}