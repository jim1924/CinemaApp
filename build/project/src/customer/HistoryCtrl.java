package customer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

import javafx.event.ActionEvent;
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
	
	InputStream in = getClass().getResourceAsStream("/assets/obj.json"); 
	JSONObject obj = JSONUtils.getJSONObjectFromFile(in);
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
						
						
						if(checkIfMovietimeHasPassed(screeningID))
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
	 * This method takes as input the ScreeningID and calculates whether the specific Screening has already been displayed
	 * @param screeningID
	 * @return
	 */
	private boolean checkIfMovietimeHasPassed(int screeningID)
	{
		Boolean checkCurrentDate=false;
		String movieDate="";
		String movieTime="";
		for (int i = 0; i < Screenings.length(); i++)
		{
			if (Screenings.getJSONObject(i).getInt("screeningID") == screeningID)
			{
				movieDate=Screenings.getJSONObject(i).getString("date");
				movieTime=Screenings.getJSONObject(i).getString("time");
			}
		}
		String[] dayMonthYearTemp=movieDate.split("/");
		String[] hourMinuteTemp=movieTime.split(":");
		Integer[] dayMonthYear=new Integer[3];
		Integer[] hourMinute=new Integer[2];
		
		dayMonthYear[0]=Integer.parseInt(dayMonthYearTemp[0]);
		dayMonthYear[1]=Integer.parseInt(dayMonthYearTemp[1]);
		dayMonthYear[2]=Integer.parseInt(dayMonthYearTemp[2]);
		hourMinute[0]=Integer.parseInt(hourMinuteTemp[0]);
		hourMinute[1]=Integer.parseInt(hourMinuteTemp[1]);
		
		Calendar now =Calendar.getInstance();
		if(now.get(Calendar.YEAR)<dayMonthYear[2])
			checkCurrentDate=true;
		else if (now.get(Calendar.YEAR)==dayMonthYear[2])
		{
			if(now.get(Calendar.MONTH)+1<dayMonthYear[1])
			{
				checkCurrentDate=true;
			}
			else if(now.get(Calendar.MONTH)+1==dayMonthYear[1])
			{
				if(now.get(Calendar.DAY_OF_MONTH)<dayMonthYear[0])
				{
					checkCurrentDate=true;
				}
				else if(now.get(Calendar.DAY_OF_MONTH)==dayMonthYear[0])
				{
					
					if(now.get(Calendar.HOUR_OF_DAY)<hourMinute[0]-1)
					{
						checkCurrentDate=true;
					}
					else
					{
						checkCurrentDate=false;
					}
				}
				else
				{
					checkCurrentDate=false;
				}
			}
			else
			{
				checkCurrentDate=false;
			}
		}
		else
		{
			checkCurrentDate=false;
		}
		return checkCurrentDate;
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
		
		
		FileWriter write = new FileWriter("src/assets/obj.json");
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

	
	//logout button
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
	
	//back button
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
