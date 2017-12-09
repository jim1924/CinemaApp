package customer;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

import application.VariableTracker;
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
 * 
 * @author Dimitris Selal
 *
 */

public class MovieSelectCtrl implements Initializable
{
	public static String selectedMovie;
	public static String selectedDate;
	public static String selectedTime;

	@FXML
	ListView<String> movieList = new ListView<>(); // movie list ListView
	@FXML
	Label description = new Label(); // movie description label
	@FXML
	ComboBox<String> movieDates = new ComboBox<>();
	@FXML
	ComboBox<String> movieTimes = new ComboBox<>();
	@FXML
	ImageView iv = new ImageView();// Place where the image of the screen will be hosted

	ObservableList<String> movieListItems = FXCollections.observableArrayList();
	ArrayList<String> movieDescription = new ArrayList<String>();
	ArrayList<String> ImagesPath = new ArrayList<String>();
	// this is in arrayList with each row having an array
	ArrayList<String[]> movieDatesList = new ArrayList<String[]>();

	/**
	 * This method is initializing the movie-selection page. It populates the "movieList" ListView
	 * with the available movies in order for the user to make a selection.
	 */

	public MovieSelectCtrl() throws IOException
	{
	}
	InputStream in = getClass().getResourceAsStream("/assets/obj.json"); 
	JSONObject obj = JSONUtils.getJSONObjectFromFile(in);
	JSONArray Movies = obj.getJSONArray("Movies");
	JSONArray Screenings = obj.getJSONArray("Screenings");

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

		for (int i = 0; i < Movies.length(); i++)
		{
			movieListItems.add(Movies.getJSONObject(i).getString("title"));
			movieDescription.add(Movies.getJSONObject(i).getString("desc"));
			ImagesPath.add(Movies.getJSONObject(i).getString("imgSrc"));
			int counter = 0;
			for (int k = 0; k < Screenings.length(); k++)
			{

				if (Screenings.getJSONObject(k).getString("title").equals(Movies.getJSONObject(i).getString("title")))
				{
					counter++;
				}
			}
			String[] tempArray = new String[counter];
			int counter2 = 0;
			for (int j = 0; j < Screenings.length(); j++)
			{
				if (Screenings.getJSONObject(j).getString("title").equals(Movies.getJSONObject(i).getString("title")))
				{
					tempArray[counter2] = Screenings.getJSONObject(j).getString("date");
					counter2++;
				}
			}
			// each row of this variable is a different movie. Each column has an array with the
			// available Dates of this movie
			movieDatesList.add(tempArray);
		}

		movieList.setItems(movieListItems);

		// populates with the first item of the movies list (to show something)
		Image image = new Image(getClass().getResourceAsStream((ImagesPath.get(0))));
		iv.setImage(image);
		description.setText(movieDescription.get(0));
		
	}

	/**
	 * This method changed the movie image, the description and the available dates when each movie
	 * is clicked
	 */
	public void clickMovie()
	{
		selectedMovie = movieList.getSelectionModel().getSelectedItem();
		// code to change the displayed description, available Dates and the displayed photo
		for (int i = 0; i < movieListItems.size(); i++)
		{
			if (movieList.getSelectionModel().getSelectedItem().equals(movieListItems.get(i)))
			{

				VariableTracker.movieTitle = movieListItems.get(i);
				VariableTracker.movieDescription = movieDescription.get(i);
				Image validImage = null;
				description.setText(movieDescription.get(i));
				try
				{
					Image image = new Image(getClass().getResourceAsStream(String.valueOf(ImagesPath.get(i))));
					validImage = image;
				} catch (NullPointerException e)
				{
					Image image = new Image(getClass().getResourceAsStream("/assets/placeholder.png"));
					validImage = image;
				}
				VariableTracker.movieImage = validImage;
				iv.setImage(validImage);
				ObservableList<String> movieDatesItems = FXCollections.observableArrayList();
				for (int j = 0; j < movieDatesList.get(i).length; j++)
				{
					movieDatesItems.add(movieDatesList.get(i)[j]);
				}
				movieDates.setItems(movieDatesItems);
			}
		}
	}

	/**
	 * This method changed the movie times, when the mouse enters the area of the time combobox
	 */

	public void mouseToTimeComboBox()
	{
		ObservableList<String> movieTimesItems = FXCollections.observableArrayList();
		selectedDate = movieDates.getSelectionModel().getSelectedItem();
		// code to change the content of the combox Times
		for (int i = 0; i < Screenings.length(); i++)
		{
			if (Screenings.getJSONObject(i).getString("title").equals(selectedMovie) && Screenings.getJSONObject(i).getString("date").equals(selectedDate))
			{
				movieTimesItems.add(Screenings.getJSONObject(i).getString("time"));
			}
		}
		movieTimes.setItems(movieTimesItems);
	}

	//back button
	public void back(ActionEvent Event) throws IOException // back button
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
	 * 
	 * @param Event
	 * @throws IOException
	 */
	public void bookNow(ActionEvent Event) throws IOException
	{
		if (movieDates.getValue() != null && movieTimes.getValue() != null)
		{
			application.VariableTracker.selectedMovie = movieList.getSelectionModel().getSelectedItem();
			application.VariableTracker.selectedDate = movieDates.getSelectionModel().getSelectedItem();
			application.VariableTracker.selectedTime = movieTimes.getSelectionModel().getSelectedItem();

			// code to go to the booking screen
			Parent availableTimes = FXMLLoader.load(getClass().getResource("/customer/SeatSelection.fxml"));
			Scene availableTimesScene = new Scene(availableTimes);
			Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
			window.setScene(availableTimesScene);
			window.show();
			availableTimesScene.getWindow().centerOnScreen();
		}

	}


	//logout button
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