package customer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.control.DatePicker;
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

public class DateSelectionCtrl implements Initializable
{

	@FXML
	ListView<String> movieList = new ListView<>(); // movie list ListView
	@FXML
	Label description = new Label(); // movie description label
	@FXML
	ImageView iv = new ImageView();// Place where the image of the screen will be hosted
	@FXML
	DatePicker movieDates = new DatePicker();
	@FXML
	ComboBox<String> movieTimes=new ComboBox<String>();

	
	ArrayList<String> ImagesPath = new ArrayList<String>();



	public DateSelectionCtrl() throws IOException
	{
	}
	JSONObject obj = JSONUtils.getJSONObjectFromFile("database.json");
	JSONArray Movies = obj.getJSONArray("Movies");
	JSONArray Screenings = obj.getJSONArray("Screenings");
	
	
	/**
	 * This method is initializing the date-selection page. It populates the available times for a specific date the user chooses
	 */

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		// populates with the first item of the movies list (to show something)
		File file = new File(Movies.getJSONObject(0).getString("imgSrc"));
		Image image = new Image(file.toURI().toString());
		iv.setImage(image);
		description.setText(Movies.getJSONObject(0).getString("desc"));
		
	}

	/**
	 * This method changed changes the available time related to the date the users clicks
	 */
	public void chooseDate()
	{
		int year=movieDates.getValue().getYear();
		int month=movieDates.getValue().getMonthValue();
		int day=movieDates.getValue().getDayOfMonth();
		String fullDate=day+"/"+month+"/"+year;
		ArrayList<String> movieTimesItems=new ArrayList<String>();
		for (int i = 0; i < Screenings.length(); i++)
		{
			
			if (fullDate.equals(Screenings.getJSONObject(i).getString("date")) && checkIfMovietimeHasPassed(Screenings.getJSONObject(i).getInt("screeningID")))
			{
				movieTimesItems.add(Screenings.getJSONObject(i).getString("time"));
				application.VariableTracker.selectedDate=Screenings.getJSONObject(i).getString("date");
			}
		}
		ObservableList<String> movieTimesObs = FXCollections.observableArrayList(movieTimesItems);
		movieTimes.setItems(movieTimesObs);
	}
	

	/**
	 * This method shows the available movies when the user has already chosen a day and a time
	 */
	public void chooseTime()
	{
		ArrayList<String> movieListItems=new ArrayList<String>();
		application.VariableTracker.selectedTime=movieTimes.getSelectionModel().getSelectedItem();
		for (int i = 0; i < Screenings.length(); i++)
		{
			if (application.VariableTracker.selectedTime!=null && application.VariableTracker.selectedDate!=null && application.VariableTracker.selectedDate.equals(Screenings.getJSONObject(i).getString("date"))&&application.VariableTracker.selectedTime.equals(Screenings.getJSONObject(i).getString("time")))
			{
				movieListItems.add(Screenings.getJSONObject(i).getString("title"));
			}
		}
		ObservableList<String> movieListObs = FXCollections.observableArrayList(movieListItems);
		movieList.setItems(movieListObs);
	}
	

	/**
	 * this method updates the picture of a movie and the description of the movie when a movie is clicked from the listView
	 */
	public void chooseMovie()
	{
		application.VariableTracker.selectedMovie=movieList.getSelectionModel().getSelectedItem();
		for (int i = 0; i < Screenings.length(); i++)
		{
			boolean checkDate=application.VariableTracker.selectedDate.equals(Screenings.getJSONObject(i).getString("date"));
			boolean checkTime=application.VariableTracker.selectedTime.equals(Screenings.getJSONObject(i).getString("time"));
			boolean checkMovie=application.VariableTracker.selectedMovie.equals(Screenings.getJSONObject(i).getString("title"));
			if (checkDate && checkTime && checkMovie)
			{
				for (int k = 0; k < Movies.length(); k++)
				{
					if (Movies.getJSONObject(k).getString("title").equals(application.VariableTracker.selectedMovie))
					{
						File file = new File(Movies.getJSONObject(k).getString("imgSrc"));
						Image image = new Image(file.toURI().toString());
						iv.setImage(image);
						description.setText(Movies.getJSONObject(k).getString("desc"));
					}
				}
			}
		}
	}

	/**
	 * This method loads the Seat Selection page after the user has chosen a movie, a date and a time
	 * 
	 * @param Event
	 * @throws IOException
	 */
	public void bookNow(ActionEvent Event) throws IOException
	{
		System.out.println(application.VariableTracker.selectedMovie+" "+application.VariableTracker.selectedDate+" "+application.VariableTracker.selectedTime);
		if (movieList.getSelectionModel().getSelectedItem()!=null && movieDates.getValue()!=null && movieTimes.getSelectionModel().getSelectedItem()!=null)
		{
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
	

	//back button
	public void back(ActionEvent Event) throws IOException 
	{

		Parent main = FXMLLoader.load(getClass().getResource("/customer/HomePage.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}
	
	/**
	 * This method takes as input the ScreeningID and calculates whether the specific Screening has already been displayed
	 * @param screeningID
	 * @return
	 */
	public boolean checkIfMovietimeHasPassed(int screeningID)
	{
		Boolean checkCurrentDate=false;
		String movieDate="";
		String movieTime="";
		for (int i = 0; i < Screenings.length(); i++)
		{
			if (Screenings.getJSONObject(i).getInt("screeningID") == screeningID)
			{
				movieDate=Screenings.getJSONObject(i).getString("date");
				movieTime=Screenings.getJSONObject(i).getString("time");
			}
		}
		String[] dayMonthYearTemp=movieDate.split("/");
		String[] hourMinuteTemp=movieTime.split(":");
		Integer[] dayMonthYear=new Integer[3];
		Integer[] hourMinute=new Integer[2];
		
		dayMonthYear[0]=Integer.parseInt(dayMonthYearTemp[0]);
		dayMonthYear[1]=Integer.parseInt(dayMonthYearTemp[1]);
		dayMonthYear[2]=Integer.parseInt(dayMonthYearTemp[2]);
		hourMinute[0]=Integer.parseInt(hourMinuteTemp[0]);
		hourMinute[1]=Integer.parseInt(hourMinuteTemp[1]);
		
		Calendar now =Calendar.getInstance();
		if(now.get(Calendar.YEAR)<dayMonthYear[2])
			checkCurrentDate=true;
		else if (now.get(Calendar.YEAR)==dayMonthYear[2])
		{
			if(now.get(Calendar.MONTH)+1<dayMonthYear[1])
			{
				checkCurrentDate=true;
			}
			else if(now.get(Calendar.MONTH)+1==dayMonthYear[1])
			{
				if(now.get(Calendar.DAY_OF_MONTH)<dayMonthYear[0])
				{
					checkCurrentDate=true;
				}
				else if(now.get(Calendar.DAY_OF_MONTH)==dayMonthYear[0])
				{
					
					if(now.get(Calendar.HOUR_OF_DAY)<hourMinute[0]-1)
					{
						checkCurrentDate=true;
					}
					else
					{
						checkCurrentDate=false;
					}
				}
				else
				{
					checkCurrentDate=false;
				}
			}
			else
			{
				checkCurrentDate=false;
			}
		}
		else
		{
			checkCurrentDate=false;
		}
		return checkCurrentDate;
	}

}
