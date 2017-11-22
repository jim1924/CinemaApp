package users;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

public class Customer extends User{

	public Customer(String email, String password) {
		super(email, password);
		try {
			registerCustomer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void registerCustomer() throws IOException
	{
		
		JSONObject obj = JSONUtils.getJSONObjectFromFile("/assets/obj.json");
		JSONArray jsonArray = obj.getJSONArray("LoginDetails");
		JSONObject temp = new JSONObject();
		temp.put("email", email);
		temp.put("password", password);
		jsonArray.put(temp);
		
		
		BufferedWriter writer= new BufferedWriter( new FileWriter("./src/assets/obj.json"));
		writer.write(obj.toString());
		writer.close();
		
		}

		
	}


