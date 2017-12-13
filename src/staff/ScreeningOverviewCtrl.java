package staff;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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

public class ScreeningOverviewCtrl implements Initializable
{
	Boolean[][] bookedSeats = new Boolean[10][10];
	 String selectedMovie ;
	 String selectedDate ;
	 String selectedTime ;
	Integer screeningID;

	public ScreeningOverviewCtrl() throws IOException {
	} 
	
	@FXML
	GridPane grid = new GridPane();
	JSONObject obj = JSONUtils.getJSONObjectFromFile("database.json");
	JSONArray screenings = obj.getJSONArray("Screenings");
	JSONArray bookings = obj.getJSONArray("Bookings");
	JSONArray customerDetails = obj.getJSONArray("CustomerDetails");

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		 selectedMovie = application.VariableTracker.selectedMovieStaff;
		 selectedDate = application.VariableTracker.selectedDateStaff;
		 selectedTime = application.VariableTracker.selectedTimeStaff;
		 System.out.println(selectedMovie+" "+selectedDate+" "+selectedTime);
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
	 * This method logs out the user when the log out button is presses
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
	 * This method moves to the previous page when the back button is pressed
	 * @param Event
	 * @throws IOException
	 */
	public void back(ActionEvent Event) throws IOException 
	{
			Parent main = FXMLLoader.load(getClass().getResource("/staff/ScreeningControl.fxml"));
			Scene loginscene = new Scene(main);
			Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
			window.setScene(loginscene);
			window.show();
			loginscene.getWindow().centerOnScreen();
	}

}