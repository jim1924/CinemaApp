//package application;
//
//import java.io.File;
//import java.io.IOException;
//
//import org.apache.commons.io.FileUtils;
//import org.json.CDL;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import com.tcg.json.JSONUtils;
//
//public class JsonExporter {
//    public static void main(String myHelpers[]){
//    JSONObject obj= new JSONObject();
//    		try{
//				obj = JSONUtils.getJSONObjectFromFile("database.json");
//			}
//			catch(Exception e){
//				System.out.println(e);
//			}
//    	JSONArray screenings = obj.getJSONArray("Screenings");
//    
//    for (int i=0; i< screenings.length(); i++ )
//    {
//    	screenings.get(i).
//    }
//
//    JSONObject output;
//    try {
//        
//
//        File file=new File("assets/fromJSON.csv");
//        String csv = CDL.toString(screenings);
//        FileUtils.writeStringToFile(file, csv);
//    } catch (JSONException e) {
//        e.printStackTrace();
//    } catch (IOException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//    }           
//    }
//
//}