package users;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class Customer.
 * @author Ahmed Afify
 */
public class Customer{
	
	/** The email. */
	private String email="";
	
	/** The password. */
	private String password="";
	
	/** The first name. */
	private String firstName="";
	
	/** The last name. */
	private String lastName="";

	/**
	 * Instantiates a new customer.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param email the email
	 * @param password the password
	 */
	public Customer(String firstName,String lastName,String email, String password) {
		this.email=email;
		this.password=password;
		this.firstName=firstName;
		this.lastName=lastName;
		try {
			registerCustomer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Register customer.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void registerCustomer() throws IOException
	{
		
		JSONObject obj = JSONUtils.getJSONObjectFromFile("database.json");
		JSONArray jsonArray = obj.getJSONArray("CustomerDetails");
		JSONArray emptyBooking = new JSONArray();
		JSONObject temp = new JSONObject();
		temp.put("email", email);
		temp.put("password", password);
		temp.put("firstName", firstName);
		temp.put("lastName", lastName);
		temp.put("bookings",emptyBooking);
		jsonArray.put(temp);
		
		
		BufferedWriter writer= new BufferedWriter( new FileWriter("database.json"));
		writer.write(obj.toString());
		writer.close();
		
		}

		
	}


