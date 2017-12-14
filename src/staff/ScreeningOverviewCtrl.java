package staff;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class ScreeningOverviewCtrl.
 * @author Ahmed Afify
 */
public class ScreeningOverviewCtrl implements Initializable
{
	
	/** The booked seats. */
	Boolean[][] bookedSeats = new Boolean[10][10];
	 
 	/** The selected movie. */
 	String selectedMovie ;
	 
 	/** The selected date. */
 	String selectedDate ;
	 
 	/** The selected time. */
 	String selectedTime ;
	
	/** The screening ID. */
	Integer screeningID;
	
	/** The Screening booked seats. */
	@FXML
	Label ScreeningBookedSeats=new Label();
	
	/** The Screening free seats. */
	@FXML
	Label ScreeningFreeSeats=new Label();
	
	/** The delete screening. */
	@FXML
	Button deleteScreening=new Button();

	/**
	 * Instantiates a new screening overview ctrl.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ScreeningOverviewCtrl() throws IOException {
	} 
	
	/** The grid. */
	@FXML
	GridPane grid = new GridPane();
	
	/** The obj. */
	JSONObject obj = JSONUtils.getJSONObjectFromFile("database.json");
	
	/** The screenings. */
	JSONArray screenings = obj.getJSONArray("Screenings");

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{

		 selectedMovie = application.VariableTracker.selectedMovieStaff;
		 selectedDate = application.VariableTracker.selectedDateStaff;
		 selectedTime = application.VariableTracker.selectedTimeStaff;
		JSONArray Screenings = screenings;
		for (int i = 0; i < Screenings.length(); i++)
		{
			// finds the specific screening
			if (selectedMovie.equals(Screenings.getJSONObject(i).getString("title")) && selectedDate.equals(Screenings.getJSONObject(i).getString("date")) && selectedTime.equals(Screenings.getJSONObject(i).getString("time")))
			{
				ScreeningBookedSeats.setText(Integer.toString(Screenings.getJSONObject(i).getInt("bookedSeats")));
				ScreeningFreeSeats.setText(Integer.toString(Screenings.getJSONObject(i).getInt("freeSeats")));
				
				if (Integer.parseInt(ScreeningBookedSeats.getText())==0)
				{
					deleteScreening.setVisible(true);
				}
				
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
	 * This method is linked to the "Delete booking" button. This button appears only when there is no booking made yet
	 *
	 * @param Event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void deleteBooking(ActionEvent Event) throws IOException 
	{
		for (int i=0;i<screenings.length();i++)
		{
			if(screeningID==screenings.getJSONObject(i).getInt("screeningID"))
			{
				screenings.remove(i);
			}
		}
		FileWriter write = new FileWriter( "database.json");
		write.write(obj.toString());
		write.close();
		// code to go to the first screen
		Parent main = FXMLLoader.load(getClass().getResource("/staff/ScreeningControl.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}
	

	
	
	/**
	 * This method logs out the user when the log out button is presses.
	 *
	 * @param Event the event
	 * @throws IOException Signals that an I/O exception has occurred.
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
	 * This method moves to the previous page when the back button is pressed.
	 *
	 * @param Event the event
	 * @throws IOException Signals that an I/O exception has occurred.
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