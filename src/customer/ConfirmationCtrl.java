package customer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * This is the controller class of the confirmation page. The user here can see the summary of his/her booking
 * @author Dimitris Selalmazidis
 *
 */
public class ConfirmationCtrl implements Initializable
{
	@FXML
	Label seatLabel = new Label();


	@FXML
	Label movie = new Label();
	@FXML
	Label date = new Label();
	@FXML
	Label time = new Label();
	@FXML
	Label bookedSeats = new Label();
	@FXML
	Label totalCost = new Label();


	/**
	 * This method is initializing the booking confirmation page. It just taked the data stored from the previous page to the class VariableTracker (application package) 
	 * and displays the summary of the booking. 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		movie.setText(application.VariableTracker.selectedMovie);
		date.setText(application.VariableTracker.selectedDate);
		time.setText(application.VariableTracker.selectedTime);
		totalCost.setText(Integer.toString(application.VariableTracker.totalCost)+"£");
		ArrayList<String> bookedSeats = new ArrayList<String>();
		int counter = 0;
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				if (SeatSelectionCtrl.seatsToBook[i][j])
				{
					bookedSeats.add(mapping(i, j));
					counter++;
					
				}
			}
		}
		String allSeats = "";
		if (counter == 1)
		{
			seatLabel.setText("Booked seat:");
			allSeats =  bookedSeats.get(0);
		} else
		{

			int count = 1;
			for (int i = 0; i < bookedSeats.size(); i++)
			{
				if (i == bookedSeats.size() - 1)
				{
					allSeats = allSeats + bookedSeats.get(i);
					count++;
				} else
				{
					allSeats = allSeats + bookedSeats.get(i) + ", ";
					count++;
				}

			}
		}
		this.bookedSeats.setText(allSeats);
	}
	
	/**
	 * This method takes as an input the index of the cinema array and converts it the the format A1,B2 etc.
	 * @param i
	 * @param j
	 * @return
	 */
	public static String mapping(int i,int j)
	{
		String row="";
		String column="";
		switch (i){
		case 0:row="J"; break;
		case 1:row="I"; break;
		case 2:row="H"; break;
		case 3:row="G"; break;
		case 4:row="F"; break;
		case 5:row="E"; break;
		case 6:row="D"; break;
		case 7:row="C"; break;
		case 8:row="B"; break;
		case 9:row="A"; break;
		}
		switch (j){
		case 0:column="1"; break;
		case 1:column="2"; break;
		case 2:column="3"; break;
		case 3:column="4"; break;
		case 4:column="5"; break;
		case 5:column="6"; break;
		case 6:column="7"; break;
		case 7:column="8"; break;
		case 8:column="9"; break;
		case 9:column="10"; break;
		}
		String rowColumn=row+""+column;
		return rowColumn;
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
	
	/**
	 * This method is linked with the home button and moves the user to the home page
	 * @param Event
	 * @throws IOException
	 */
	public void home(ActionEvent Event) throws IOException
	{
		// code to go to home
		Parent main = FXMLLoader.load(getClass().getResource("/customer/HomePage.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}

}