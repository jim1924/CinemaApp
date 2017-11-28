package customer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
	ArrayList<String> movieDescription=new ArrayList<String>();
	ArrayList<String> ImagesPath=new ArrayList<String>();
 	ObservableList<String> movieTimesItems = FXCollections.observableArrayList();
 	ArrayList<String[]> movieTimesList = new ArrayList<String[]>();  

	/**
	 * This method is initializing the movie-selection page.
	 * It populates the "movieList" Listview with the available movies in order for the user to make a selection.
	 */
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		JSONObject obj = JSONUtils.getJSONObjectFromFile("/assets/obj.json");
		JSONArray jsonArray = obj.getJSONArray("Movies");
		JSONArray jsonArray2 = obj.getJSONArray("Screenings");
		JSONArray Movies=jsonArray;
		JSONArray Screenings=jsonArray2;
		
		for (int i=0;i<Movies.length();i++)
		{
			movieListItems.add(Movies.getJSONObject(i).getString("title"));
			movieDescription.add(Movies.getJSONObject(i).getString("desc"));
			ImagesPath.add(Movies.getJSONObject(i).getString("imgSrc"));
			String[] tempArray=new String[100];
			for (int j=0;j<Screenings.length();j++)
			{
				if (Screenings.getJSONObject(j).getString("title").equals(Movies.getJSONObject(i).getString("title")))
				{
					tempArray[j]=Screenings.getJSONObject(j).getString("date");
				}
			}
			movieTimesList.add(tempArray);
			
			
		}
		
		
		movieList.setItems(movieListItems);

		Image image = new Image(getClass().getResourceAsStream("/assets/10.jpg"));
		iv.setImage(image);
		description.setText(movieDescription.get(0));
	
	}
	
	
	/**
	 * This method changed the movie image, the description and the available times when each movie is clicked
	 */
	public void clickmovie(){

			
			//code to change the displayed photo
			System.out.println("clicked on " + movieList.getSelectionModel().getSelectedItem());
			
			//code to change the displayed description
			for (int i=0;i<movieListItems.size();i++){
				if(movieList.getSelectionModel().getSelectedItem().equals(movieListItems.get(i)))
				{
					description.setText(movieDescription.get(i));
					System.out.println(String.valueOf(ImagesPath.get(i)));
					Image image = new Image(getClass().getResourceAsStream(String.valueOf(ImagesPath.get(i))));
					iv.setImage(image);
				}
			}
			
			
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
