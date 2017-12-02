package customer;

import java.io.BufferedWriter;
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
	Boolean[][] seatsToBook = new Boolean[10][10];
	String selectedMovie = MovieSelectCtrl.selectedMovie;
	String selectedDate = MovieSelectCtrl.selectedDate;
	String selectedTime = MovieSelectCtrl.selectedTime;
	Integer screeningID;

	@FXML
	GridPane grid = new GridPane();
	JSONObject obj = JSONUtils.getJSONObjectFromFile("/assets/obj.json");
	JSONArray jsonArray = obj.getJSONArray("Screenings");
	JSONArray jsonArray2 = obj.getJSONArray("Bookings");

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{

		JSONArray Screenings = jsonArray;
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
						System.out.println("this seat is booked");
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

	public void back(ActionEvent Event) throws IOException // back button
	{

		Parent main = FXMLLoader.load(getClass().getResource("/customer/MovieSelect.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}

	
	public void confirm(ActionEvent Event) throws IOException // book now button
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
	
	private void updateTheDataBase() throws IOException{
		JSONArray Screenings = jsonArray;
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
					else if (seatsToBook[row][column])
					availabilityObj.getJSONObject(j).put("booked", true);
				}

			}
		}
		JSONArray Bookings = jsonArray2;
		int bookingID=0;
		//this doesn't work
		//This for loops finds an available booking ID counting from 0
		for (int i = 0; i < Bookings.length(); i++,bookingID++)
		{
			if(Bookings.getJSONObject(i).getInt("bookingID")==bookingID)
				continue;
			else
			{
				bookingID=Bookings.getJSONObject(i).getInt("bookingID");
				break;
			}
		}
		System.out.println(bookingID);
		JSONObject newBooking = new JSONObject();
		newBooking.put("customerEmail", "i have to find it");
		newBooking.put("screeningID", screeningID);
		newBooking.put("bookingID", bookingID);
		String[] seats=new String[100];
		int counter=0;
		for (int i=0;i<10;i++)
		{
			for (int j=0;j<10;j++)
			{
					if(seatsToBook[i][j])
					{
						seats[counter]=i+","+j;
						counter++;
					}
			}
		}
/*		newBooking.put("seats", seats);
		jsonArray2.put(jsonArray2.length(),newBooking);
		BufferedWriter writer= new BufferedWriter( new FileWriter("./src/assets/obj.json"));
		writer.write(obj.toString());
		writer.close();*/
		
		
	}

	public void logout(ActionEvent Event) throws IOException // logout button
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