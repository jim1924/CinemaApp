package customer;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

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

public class SeatSelectionCtrl implements Initializable
{
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
	JSONObject obj = JSONUtils.getJSONObjectFromFile("./src/assets/obj.json");
	JSONArray screenings = obj.getJSONArray("Screenings");
	JSONArray bookings = obj.getJSONArray("Bookings");
	JSONArray customerDetails = obj.getJSONArray("CustomerDetails");

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
				if (bookedSeats[i][j]!=null && bookedSeats[i][j])
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
						if (seatsToBook[GridPane.getRowIndex(element)][GridPane.getColumnIndex(element)] == null || seatsToBook[GridPane.getRowIndex(element)][GridPane.getColumnIndex(element)] == false)
						{
							getNodeFromGridPane(grid, GridPane.getColumnIndex(element), GridPane.getRowIndex(element)).setStyle("-fx-background-color: green;");
							seatsToBook[GridPane.getRowIndex(element)][GridPane.getColumnIndex(element)] = true;
						} else
						{
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
	// back button
	public void back(ActionEvent Event) throws IOException 
	{
		
		if (application.VariableTracker.selectMovieFirst)
		{
			Parent main = FXMLLoader.load(getClass().getResource("/customer/MovieSelect.fxml"));
			Scene loginscene = new Scene(main);
			Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
			window.setScene(loginscene);
			window.show();
			loginscene.getWindow().centerOnScreen();
		}
		else
		{
			Parent main = FXMLLoader.load(getClass().getResource("/customer/DateSelection.fxml"));
			Scene loginscene = new Scene(main);
			Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
			window.setScene(loginscene);
			window.show();
			loginscene.getWindow().centerOnScreen();
		}

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
				if (seatsToBook[i][j]!=null)
				{
					atLeastOneSeatLelected=true;
				}
			}
		}
		if(atLeastOneSeatLelected)
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
		//This for loops finds an available booking ID counting from 0
		for (int i = 0; i < Bookings.length(); i++)
		{
			if(Bookings.getJSONObject(i).getInt("bookingID")==bookingID)
			{
				bookingID++;
				continue;
			}
		}
		
		JSONObject newBooking = new JSONObject();
		newBooking.put("customerEmail", application.VariableTracker.custEmail);
		newBooking.put("screeningID", screeningID);
		newBooking.put("bookingID", bookingID);
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
		
		FileWriter write = new FileWriter( "./src/assets/obj.json");
		write.write(obj.toString());
		write.close();

		
	}
	// logout button
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