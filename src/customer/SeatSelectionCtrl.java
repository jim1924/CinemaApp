package customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SeatSelectionCtrl implements Initializable
{

	@FXML
	ListView<String> movieList = new ListView<>(); //movie list ListView
	@FXML
	Label description = new Label(); //movie description label
	@FXML
	ComboBox<String> movieTimes=new ComboBox<>();
	@FXML
	ImageView iv = new ImageView();// Place where the image of the screen will be hosted
	ObservableList<String> movieListItems = FXCollections.observableArrayList("Single", "Double", "Suite", "Family App", "Suite",
			"Family App", "Suite", "Family App", "Suite", "Family App", "Suite", "Family App");
	
	ObservableList<String> movieTimesItems = FXCollections.observableArrayList("Monday 19/12 10.30", "Wednesday 20/12 17.00", "Wednesday 20/12 19.00", "Thursday 21/12 20.00","Thursday 21/12 21.00","Friday 22/12 21.00");

	@Override
	public void initialize(URL location, ResourceBundle resources) //values when the screen loads
	{
		movieList.setItems(movieListItems);

		Image image = new Image(getClass().getResourceAsStream("/assets/10.jpg"));
		iv.setImage(image);
		description.setText("A bounty hunting scam joins two men in an uneasy alliance against a third in a race to find a fortune in gold buried in a remote cemetery. ");
		movieTimes.setItems(movieTimesItems);
	
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

	public void booknow(ActionEvent Event) throws IOException //book now button
	{
		
		//code to go to the booking screen 
		Parent availableTimes = FXMLLoader.load(getClass().getResource("/customer/Confirmation.fxml"));
		Scene availableTimesScene = new Scene(availableTimes);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(availableTimesScene);
		window.show();
		availableTimesScene.getWindow().centerOnScreen();

		 
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