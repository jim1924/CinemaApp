package customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

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

/**
 * This is the controller class of the movie selection page
 * @author Dimitris Selal
 *
 */

public class MovieSelectCtrl implements Initializable
{

	@FXML
	ListView<String> movieList = new ListView<>(); //movie list ListView
	@FXML
	Label description = new Label(); //movie description label
	@FXML
	ComboBox<String> movieTimes=new ComboBox<>();
	@FXML
	ImageView iv = new ImageView();// Place where the image of the screen will be hosted
	ObservableList<String> movieListItems = FXCollections.observableArrayList();
	
	ObservableList<String> movieTimesItems = FXCollections.observableArrayList("Monday 19/12 10.30", "Wednesday 20/12 17.00", "Wednesday 20/12 19.00", "Thursday 21/12 20.00","Thursday 21/12 21.00","Friday 22/12 21.00");

	/**
	 * This method is initializing the movie-selection page.
	 * It populates the "movieList" Listview with the available movies in order for the user to make a selection.
	 */
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		JSONObject obj = JSONUtils.getJSONObjectFromFile("/assets/obj.json");
		JSONArray jsonArray = obj.getJSONArray("Movies");
		JSONArray MovieList=jsonArray;
		for (int i=0;i<MovieList.length();i++)
		{
			movieListItems.add(MovieList.getJSONObject(i).getString("title"));
		}
		
		movieList.setItems(movieListItems);

		Image image = new Image(getClass().getResourceAsStream("/assets/10.jpg"));
		iv.setImage(image);
		description.setText("A bounty hunting scam joins two men in an uneasy alliance against a third in a race to find a fortune in gold buried in a remote cemetery. ");
		movieTimes.setItems(movieTimesItems);
	
	}
	
	
	/**
	 * This method changed the movie image, the description and the available times when each movie is clicked
	 */
	public void clickmovie(){

			
			//code to change the displayed photo
			System.out.println("clicked on " + movieList.getSelectionModel().getSelectedItem());
			
			//code to change the displayed description
			System.out.println("Description changed");
			
			//code to change the available times
	}

	/**
	 * This method allows the user to go one screen back when the back button is pressed
	 * @param Event
	 * @throws IOException
	 */
	public void back(ActionEvent Event) throws IOException //back button
	{

		Parent main = FXMLLoader.load(getClass().getResource("/customer/HomePage.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}

	/**
	 * This method loads the Seat Selection page after the user has chosen a movie and a time
	 * @param Event
	 * @throws IOException
	 */
	public void booknow(ActionEvent Event) throws IOException 
	{
		
		//code to go to the booking screen 
		Parent availableTimes = FXMLLoader.load(getClass().getResource("/customer/SeatSelection.fxml"));
		Scene availableTimesScene = new Scene(availableTimes);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(availableTimesScene);
		window.show();
		availableTimesScene.getWindow().centerOnScreen();

		 
	}
	
	
	/**
	 * This method allows the user to log out when the Log out button is pressed
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
