package customer;

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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AvailableTimesCtrl implements Initializable
{

	@FXML
	GridPane grid=new GridPane();
	@FXML
	Label day1=new Label();
	@FXML
	Label day2=new Label();
	@FXML
	Label day3=new Label();
	@FXML
	Label day4=new Label();
	@FXML
	Label day5=new Label();
	@FXML
	Label day6=new Label();
	

	@Override
	public void initialize(URL location, ResourceBundle resources) //values when the screen loads
	{
		//grid.setGridLinesVisible(true);
	
	}
	

	public void back(ActionEvent Event) throws IOException //back button
	{

		Parent main = FXMLLoader.load(getClass().getResource("/customer/MovieSelect.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}

	public void seatselection(ActionEvent Event) throws IOException //seat selection button
	{
		
		//code to go to the booking screen 

		 
	}
	
	public void logout(ActionEvent Event) throws IOException //logout button
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
