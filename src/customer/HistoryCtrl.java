package customer;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HistoryCtrl implements Initializable
{
	public HistoryCtrl() throws IOException
	{
	}
	@FXML
	Accordion accd = new Accordion();
	public Integer numberOfBooking = 0;
	

	JSONObject obj = JSONUtils.getJSONObjectFromFile("./src/assets/obj.json");
	JSONArray Bookings = obj.getJSONArray("Bookings");
	JSONArray Screenings = obj.getJSONArray("Screenings");
	JSONArray CustomerDetails = obj.getJSONArray("CustomerDetails");

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		String custEmail=application.VariableTracker.custEmail;

		
		int countName=0;
		//searches all the bookings
		for (int i=0;i<Bookings.length();i++)
		{
			//if in there is a booking with the e-mail of customer in question
			if( Bookings.getJSONObject(i).getString("customerEmail").equals(custEmail))
			{
				countName++;
				int bookingID=Bookings.getJSONObject(i).getInt("bookingID");
				int screeningID=Bookings.getJSONObject(i).getInt("screeningID");
				//we took the key from the booking and now we search the screening having the screeningID
				for(int j=0;j<Screenings.length();j++)
				{
					if (Screenings.getJSONObject(j).getInt("screeningID")==(screeningID))
					{
						JSONArray bookedSeatsArray=Bookings.getJSONObject(i).getJSONArray("bookedSeats");
						accd.setPrefSize(500, 250);
						TitledPane titledPane = new TitledPane();
						GridPane grid = new GridPane();
						titledPane.setText("Booking Number: "+(countName));
						grid.setVgap(10);
						grid.setHgap(15);
						
						grid.setPadding(new Insets(5, 5, 5, 5));//margin around the whole grid
						grid.add(new Label("Movie:"), 0, 0);
						grid.add(new Label(Screenings.getJSONObject(j).getString("title")), 1, 0);
						grid.add(new Label("Date:"), 0, 1);
						grid.add(new Label(Screenings.getJSONObject(j).getString("date")), 1, 1);
						grid.add(new Label("Time: "), 0, 2);
						grid.add(new Label(Screenings.getJSONObject(j).getString("time")), 1, 2);    
						grid.add(new Label("Booked Seats:"), 0, 3);
						String bookedSeats=arrayToStringConverter(Bookings.getJSONObject(i).getJSONArray("bookedSeats"));
						grid.add(new Label(bookedSeats), 1, 3);
						
						//presentDate();
						if(true) //condition if the date of the screening has passed
						{
							Button deleteBooking=new Button("Delete Booking");
							grid.add((deleteBooking), 0, 8,2,1);
							deleteBooking.setOnAction(new EventHandler<ActionEvent>() {
							    @Override public void handle(ActionEvent e) {
							    	try{
							    		deleteTheBooking(bookingID,screeningID,bookedSeatsArray);
							    		Parent main = FXMLLoader.load(getClass().getResource("/customer/History.fxml"));
							    		Scene loginscene = new Scene(main);
							    		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
							    		window.setScene(loginscene);
							    		window.show();
							    		loginscene.getWindow().centerOnScreen();

							    	}
							    	catch(IOException ex)
							    	{
							    		System.out.println(ex);
							    	}
							    }
							});
						}
						titledPane.setContent(grid);
						accd.getPanes().addAll(titledPane);
					}
				}
			}
		}
	}

	

	/**
	 * This method "reverses" all the changes made when a booking was made.
	 * (removes the specific booking, makes the seats available again and removes the registered bookingID from the customerDetails object)
	 * @param bookingID This is the bookingID taken from the initialize method
	 * @param screeningID This is the screeningID taken from the initialize method
	 * @param bookedSeatsArray This is the array of booked seats registered in the bookings object
	 * @throws IOException
	 */
	private void deleteTheBooking(int bookingID, int screeningID, JSONArray bookedSeatsArray) throws IOException
	{
		
		//this for loop,searches for the specific bookingID in the bookings object and removes it
		for(int i=0;i<Bookings.length();i++)
		{
			if(Bookings.getJSONObject(i).getInt("bookingID")==bookingID)
			{
				Bookings.remove(i);
			}
		}
		//this for loop,searches in the customer details object the specific bookingID and removes it.
		for(int i=0;i<CustomerDetails.length();i++)
		{
			if(CustomerDetails.getJSONObject(i).getString("email").equals(application.VariableTracker.custEmail))
			{
				for(int j=0;j<CustomerDetails.getJSONObject(i).getJSONArray("bookings").length();j++)
				{
					if (CustomerDetails.getJSONObject(i).getJSONArray("bookings").getInt(j)==bookingID)
					{
						CustomerDetails.getJSONObject(i).getJSONArray("bookings").remove(j);
					}
				}
			}
		}

		//this loop searches all the screening, finds the screening with the specific screeningID and reverses all the seats from booked to not-booked
		for(int i=0;i<Screenings.length();i++)
		{
			if (Screenings.getJSONObject(i).getInt("screeningID")==screeningID)
			{
				for (int j=0;j<Screenings.getJSONObject(i).getJSONArray("seats").length();j++)
				{
					for(int k=0;k<bookedSeatsArray.length();k++)
					{
						if(bookedSeatsArray.get(k).equals(Screenings.getJSONObject(i).getJSONArray("seats").getJSONObject(j).getString("coor")))
						{
							Screenings.getJSONObject(i).getJSONArray("seats").getJSONObject(j).put("booked", false);
						}
					}
				}
			}

		}
		FileWriter write = new FileWriter( "./src/assets/obj.json");
		write.write(obj.toString());
		write.close();
		
	}
	

	/**
	 * This method takes as input the JSONArray with all the seats and return a string that can been shown to the customer
	 * @param jsonArray
	 * @return
	 */
	private String arrayToStringConverter(JSONArray jsonArray)
	{
		String allSeats="";
		for (int i=0;i<jsonArray.length();i++)
		{
			int row = Integer.parseInt(Character.toString(jsonArray.getString(i).charAt(0)));
			int column = Integer.parseInt(Character.toString(jsonArray.getString(i).charAt(2)));
			String seat=ConfirmationCtrl.mapping(row, column);
			if(i==jsonArray.length()-1)
			{
				allSeats=allSeats+seat;
			}
			else
			allSeats=allSeats+seat+", ";
		}
		
		return allSeats;
	}




	public void presentDate()
	{
		Calendar now =Calendar.getInstance();
		System.out.println(now.get(Calendar.YEAR));
		System.out.println(now.get(Calendar.MONTH));
		System.out.println(now.get(Calendar.DAY_OF_MONTH));
		System.out.println(now.get(Calendar.HOUR_OF_DAY));
		System.out.println(now.get(Calendar.MINUTE));
		int year=now.get(Calendar.YEAR);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * This method allows the user to log out when the Log out button is pressed
	 * 
	 * @param Event
	 * @throws IOException
	 */
	public void logout(ActionEvent Event) throws IOException
	{
		// code to go to the first screen
		Parent main = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}
	
	/**
	 * This method allows the user to go one screen back when the back button is pressed
	 * 
	 * @param Event
	 * @throws IOException
	 */
	public void back(ActionEvent Event) throws IOException // back button
	{

		Parent main = FXMLLoader.load(getClass().getResource("/customer/HomePage.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}





	
	
	
	


	
}
