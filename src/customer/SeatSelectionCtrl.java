package customer;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

import application.VariableTracker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * This is the controller class of the seat selection page. The user here has already selected a combination of Movie-Day-Time and this page shows the available seats to book
 * The user can select 1 or more seats to book but of course they can't select a pre-booked one
 * 
 * @author Dimitris Selalmazidis
 *
 */
public class SeatSelectionCtrl implements Initializable
{
	int chosenSeats=0;
	Boolean[][] bookedSeats = new Boolean[10][10];
	static Boolean[][] seatsToBook = new Boolean[10][10];
	 String selectedMovie ;
	 String selectedDate ;
	 String selectedTime ;
	Integer screeningID;

	public SeatSelectionCtrl() throws IOException {
	} 
	
	@FXML
	GridPane grid = new GridPane();
	JSONObject obj = JSONUtils.getJSONObjectFromFile("database.json");
	JSONArray screenings = obj.getJSONArray("Screenings");
	JSONArray bookings = obj.getJSONArray("Bookings");
	JSONArray customerDetails = obj.getJSONArray("CustomerDetails");

	/**
	 * This method is initializing the seat selection page. It reads from the database for a given combination of movie-day-time and populates the grid with buttons
	 * If a seat is booked then the color if the button is red and the user cannot interact with it. Otherwise, the user can click any button to select a seat
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		 selectedMovie = application.VariableTracker.selectedMovie;
		 selectedDate = application.VariableTracker.selectedDate;
		 selectedTime = application.VariableTracker.selectedTime;
		JSONArray Screenings = screenings;
		for (int i = 0; i < Screenings.length(); i++)
		{
			// finds the specific screening
			if (selectedMovie.equals(Screenings.getJSONObject(i).getString("title")) && selectedDate.equals(Screenings.getJSONObject(i).getString("date")) && selectedTime.equals(Screenings.getJSONObject(i).getString("time")))
			{
				screeningID=Screenings.getJSONObject(i).getInt("screeningID");
				// creates an object with the availability of each seat
				JSONArray availabilityObj = Screenings.getJSONObject(i).getJSONArray("seats");
				// loops through each object to identify if each seat is booked
				// or not
				for (int j = 0; j < availabilityObj.length(); j++)
				{
					
					int row;
					int column;
					row = Integer.parseInt(Character.toString((availabilityObj.getJSONObject(j).getString("coor").charAt(0))));
					column = Integer.parseInt(Character.toString((availabilityObj.getJSONObject(j).getString("coor").charAt(2))));
					bookedSeats[row][column] = (availabilityObj.getJSONObject(j).getBoolean("booked"));
					seatsToBook[row][column]=false;//populates the array which stores the seats that are going to booked
				}

			}
		}

		grid.setVgap(5);
		grid.setHgap(5);
		// populates the GridPane. If the seat is booked, then the background colour is red.
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				grid.setGridLinesVisible(false);
				Button button = new Button();
				button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				button.setAlignment(Pos.CENTER);
				if (bookedSeats[i][j]!=null && bookedSeats[i][j] )
				{
					button.setStyle("-fx-background-color: red;");
				}
				GridPane.setConstraints(button, j, i);
				grid.getChildren().add(button);
			}
		}

		// This for loop constantly listens for a mouseclick and handles the event. If the user clicks a pre-booked seat then nothing happens
		// If the user click an available seat then it turns green. It they click it again, then it goes back to default.
		for (Node element : grid.getChildren())
		{
			element.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
			{
				@Override
				public void handle(MouseEvent event)
				{
					// if the seat is not booked from another person
					if (bookedSeats[GridPane.getRowIndex(element)][GridPane.getColumnIndex(element)]!=null && !bookedSeats[GridPane.getRowIndex(element)][GridPane.getColumnIndex(element)])
					{
						// if the seat is empty, turn it into green
						if (seatsToBook[GridPane.getRowIndex(element)][GridPane.getColumnIndex(element)] == null || seatsToBook[GridPane.getRowIndex(element)][GridPane.getColumnIndex(element)] == false && chosenSeats<VariableTracker.totalSeatsToBook)
						{
							chosenSeats=chosenSeats+1;
							getNodeFromGridPane(grid, GridPane.getColumnIndex(element), GridPane.getRowIndex(element)).setStyle("-fx-background-color: green;");
							seatsToBook[GridPane.getRowIndex(element)][GridPane.getColumnIndex(element)] = true;
						} else if(seatsToBook[GridPane.getRowIndex(element)][GridPane.getColumnIndex(element)] == true)
						{
							chosenSeats=chosenSeats-1;
							seatsToBook[GridPane.getRowIndex(element)][GridPane.getColumnIndex(element)] = false;
							getNodeFromGridPane(grid, GridPane.getColumnIndex(element), GridPane.getRowIndex(element)).setStyle("");
						}
					}
					else if(bookedSeats[GridPane.getRowIndex(element)][GridPane.getColumnIndex(element)]==null)
						System.out.println("This seat is not registered as free or booked");
					else
						System.out.println("this seat is already booked");
				}
			});
		}

	}

	/**
	 * This function returns the node, when a button of the grid is clicked
	 * 
	 * @param gridPane
	 * @param col
	 *            This is the column of the click
	 * @param row
	 *            This is the row of the click
	 * @return return the specific button the user has clicked
	 */
	private Node getNodeFromGridPane(GridPane gridPane, int col, int row)
	{
		for (Node node : gridPane.getChildren())
		{
			if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row)
			{
				return node;
			}
		}
		return null;
	}
	/**
	 * This method is linked with the back button and moves the user to the previous screen
	 * @param Event
	 * @throws IOException
	 */
	public void back(ActionEvent Event) throws IOException 
	{
			Parent main = FXMLLoader.load(getClass().getResource("/customer/SeatType.fxml"));
			Scene loginscene = new Scene(main);
			Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
			window.setScene(loginscene);
			window.show();
			loginscene.getWindow().centerOnScreen();
	}

	
	/**
	 * This method initializes the booking process. It checks that the customer has clicked a seat,calls the updateDatabase method
	 * and moves to the confirmation page
	 * @param Event
	 * @throws Exception
	 */
	public void confirm(ActionEvent Event) throws Exception // book now button
	{
		boolean atLeastOneSeatLelected=false;
		for (int i=0;i<10;i++)
		{
			for (int j=0;j<10;j++)
			{
				if (seatsToBook[i][j]!=null && seatsToBook[i][j]!=false)
				{
					atLeastOneSeatLelected=true;
					break;
				}
			}
		}
		if(atLeastOneSeatLelected && chosenSeats==VariableTracker.totalSeatsToBook)
		{
			updateTheDataBase();
			// code to go to the booking screen
			Parent availableTimes = FXMLLoader.load(getClass().getResource("/customer/Confirmation.fxml"));
			Scene availableTimesScene = new Scene(availableTimes);
			Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
			window.setScene(availableTimesScene);
			window.show();
			availableTimesScene.getWindow().centerOnScreen();
		}

	}
	
	/**
	 * This method updates the json file with the relevant data
	 * It creates a new booking object, searches the specific screening and makes the selected seats booked,
	 *  and registers the booking ID the the CustomerDetails object
	 * @throws Exception
	 */
	private void updateTheDataBase() throws Exception{
		JSONArray Screenings = screenings;
		for (int i = 0; i < Screenings.length(); i++)
		{
			// finds the specific screening
			if (selectedMovie.equals(Screenings.getJSONObject(i).getString("title")) && selectedDate.equals(Screenings.getJSONObject(i).getString("date")) && selectedTime.equals(Screenings.getJSONObject(i).getString("time")))
			{
				Integer bookedSeats=Screenings.getJSONObject(i).getInt("bookedSeats");
				Integer freeSeats=Screenings.getJSONObject(i).getInt("freeSeats");
				Screenings.getJSONObject(i).put("bookedSeats", bookedSeats+1);
				Screenings.getJSONObject(i).put("freeSeats", freeSeats-1);
				
				// creates an object with the availability of each seat
				JSONArray availabilityObj = Screenings.getJSONObject(i).getJSONArray("seats");
				// loops through each object to identify if each seat is booked
				// or not
				for (int j = 0; j < availabilityObj.length(); j++)
				{
					int row;
					int column;
					row = Integer.parseInt(Character.toString((availabilityObj.getJSONObject(j).getString("coor").charAt(0))));
					column = Integer.parseInt(Character.toString((availabilityObj.getJSONObject(j).getString("coor").charAt(2))));
					if (availabilityObj.getJSONObject(j).getBoolean("booked"))
					{
						continue;
					}
					else if (seatsToBook[row][column]){
						availabilityObj.getJSONObject(j).put("booked", true);
					}
					
				}

			}
		}
		JSONArray Bookings = bookings;
		int bookingID=0;
		boolean exists;
		//This for loops finds an available booking ID counting from 0
		do
		{
		exists=false;
		bookingID++;
		for (int i = 0; i < Bookings.length(); i++)
		{
			if(Bookings.getJSONObject(i).getInt("bookingID")==bookingID)
			{
				exists=true;
			}
		}
		}
		while(exists);
		
		JSONObject newBooking = new JSONObject();
		newBooking.put("customerEmail", application.VariableTracker.custEmail);
		newBooking.put("screeningID", screeningID);
		newBooking.put("bookingID", bookingID);
		newBooking.put("totalCost", application.VariableTracker.totalCost);
		ArrayList<String> seats=new ArrayList<String>();
		for (int i=0;i<10;i++)
		{
			for (int j=0;j<10;j++)
			{
					if(seatsToBook[i][j])
					{
						seats.add(i+","+j);
					}
			}
		}
		newBooking.put("bookedSeats", seats.toArray());
		bookings.put(bookings.length(),newBooking);
		
		JSONArray CustomerDetails = customerDetails;
		
		
		for (int i = 0; i < CustomerDetails.length(); i++)
		{
			// finds the specific customer
			if (application.VariableTracker.custEmail.equals(CustomerDetails.getJSONObject(i).getString("email")))
			{
				customerDetails.getJSONObject(i).getJSONArray("bookings").put(bookingID);

			}
		}
		
		FileWriter write = new FileWriter( "database.json");
		write.write(obj.toString());
		write.close();

		
	}
	/**
	 * This method is linked with the logout button and logs out the user
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

}