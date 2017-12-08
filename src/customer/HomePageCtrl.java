package customer;
import application.*;
import java.io.IOException;
import java.net.URL;
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

public class HomePageCtrl implements Initializable {
	
	@FXML
	Label name=new Label();
	//logout button
	public void logout(ActionEvent Event) throws IOException
	{
		//code to return to the first 
		Parent main = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}

	/**
	 * This method goes to a page where the user can select a movie they want to see
	 * @param Event
	 * @throws IOException
	 */
	public void goToMovieSelect(ActionEvent Event) throws IOException
	{
		application.VariableTracker.selectMovieFirst=true;
		Parent movieSelect = FXMLLoader.load(getClass().getResource("/customer/MovieSelect.fxml"));
		Scene movieSelectScene = new Scene(movieSelect);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(movieSelectScene);
		window.show();
		movieSelectScene.getWindow().centerOnScreen();
	}
	

	/**
	 * This method goes to a page where the user chooses a date they want to see a movie
	 * @param Event
	 * @throws IOException
	 */
	public void goToDateSelection(ActionEvent Event) throws IOException
	{
		application.VariableTracker.selectMovieFirst=false;
		Parent movieSelect = FXMLLoader.load(getClass().getResource("/customer/DateSelection.fxml"));
		Scene movieSelectScene = new Scene(movieSelect);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(movieSelectScene);
		window.show();
		movieSelectScene.getWindow().centerOnScreen();
	}

	/**
	 * This method goes to a the profile page of the user
	 * @param Event
	 * @throws IOException
	 */
	public void goToProfile(ActionEvent Event) throws IOException
	{
		Parent movieSelect = FXMLLoader.load(getClass().getResource("/customer/Profile.fxml"));
		Scene movieSelectScene = new Scene(movieSelect);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(movieSelectScene);
		window.show();
		movieSelectScene.getWindow().centerOnScreen();
	}
	

	/**
	 * This method goes to a the history page where the customer can view his/her booking
	 * @param Event
	 * @throws IOException
	 */
	public void goToHistory(ActionEvent Event) throws IOException
	{
		Parent movieSelect = FXMLLoader.load(getClass().getResource("/customer/History.fxml"));
		Scene movieSelectScene = new Scene(movieSelect);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(movieSelectScene);
		window.show();
		movieSelectScene.getWindow().centerOnScreen();
	}
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		name.setText("Welcome Mr. "+application.VariableTracker.custLastName);

	}
	
	

}
