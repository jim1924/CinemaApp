package staff;

import java.io.File;
import java.io.IOException;
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



public class StaffMovieControlCtrl implements Initializable {
	
	
	@FXML
	ListView<String> movieList = new ListView<>(); // movie list ListView
	@FXML
	Label description = new Label(); // movie description label
	@FXML
	ComboBox<String> movieTimes = new ComboBox<>();
	@FXML
	ImageView iv = new ImageView();// Place where the image of the screen will be hosted

	ObservableList<String> movieListItems = FXCollections.observableArrayList();
	ArrayList<String> movieDescription = new ArrayList<String>();
	ArrayList<String> ImagesPath = new ArrayList<String>();
	// this is in arrayList with each row having an array
	ArrayList<String[]> movieTimesList = new ArrayList<String[]>();

	/**
	 * This method is initializing the movie-selection page. It populates the "movieList" ListView
	 * with the available movies in order for the user to make a selection.
	 */
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		JSONObject obj = new JSONObject();
		JSONArray Movies=new JSONArray();
		JSONArray Screenings=new JSONArray();
		try
		{
			obj = JSONUtils.getJSONObjectFromFile("database.json");
			Movies = obj.getJSONArray("Movies");
			 Screenings = obj.getJSONArray("Screenings");
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		

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
					tempArray[counter2] = "Date: " + Screenings.getJSONObject(j).getString("date") + " Time: "
							+ Screenings.getJSONObject(j).getString("time");
					counter2++;
				}
			}
			movieTimesList.add(tempArray);
		}

		movieList.setItems(movieListItems);
		
		File file = new File(ImagesPath.get(0));
		Image image = new Image(file.toURI().toString());
		iv.setImage(image);
		description.setText(movieDescription.get(0));

	}
	
	public void goBack(ActionEvent Event) throws IOException {
		Parent main = FXMLLoader.load(getClass().getResource("/staff/StaffHomePage.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}
	public void logOut(ActionEvent Event) throws IOException
	{
		// code to go to the first screen
		Parent main = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}
	public void goToScreeningControl(ActionEvent Event) throws IOException {
		if(movieList.getSelectionModel().getSelectedItem()!=null)
		{
			application.VariableTracker.selectedMovieStaff=movieList.getSelectionModel().getSelectedItem();
			Parent main = FXMLLoader.load(getClass().getResource("/staff/ScreeningControl.fxml"));
			Scene loginscene = new Scene(main);
			Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
			window.setScene(loginscene);
			window.show();
			loginscene.getWindow().centerOnScreen();
		}

	}
	/**
	 * This method changed the movie image, the description and the available times when each movie
	 * is clicked
	 */
	public void clickMovie()
	{

		// code to change the displayed photo
		System.out.println("clicked on " + movieList.getSelectionModel().getSelectedItem());

		// code to change the displayed description, available times and the displayed photo
		for (int i = 0; i < movieListItems.size(); i++)
		{
			if (movieList.getSelectionModel().getSelectedItem().equals(movieListItems.get(i)))
			{ Image validImage = null;
			VariableTracker.movieTitle=movieListItems.get(i);
			VariableTracker.movieDescription=movieDescription.get(i);
				description.setText(movieDescription.get(i));
				try {
					File file = new File(ImagesPath.get(i));
					Image image = new Image(file.toURI().toString());
					validImage = image;
				}
				catch (NullPointerException e)
				{
					File file = new File("assets/placeholder.png");
					Image image = new Image(file.toURI().toString());
					validImage = image;
				}
				VariableTracker.movieImage=validImage;
				iv.setImage(validImage);
				ObservableList<String> movieTimesItems = FXCollections.observableArrayList();
				for (int j = 0; j < movieTimesList.get(i).length; j++)
				{
					movieTimesItems.add(movieTimesList.get(i)[j]);
				}
				movieTimes.setItems(movieTimesItems);
			}
		}

		// code to change the available times

	}
	public void goToMovieAdder(ActionEvent Event) throws IOException {
		Parent main = FXMLLoader.load(getClass().getResource("/staff/MovieAdder.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}
}
