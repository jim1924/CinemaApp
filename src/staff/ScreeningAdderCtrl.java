package staff;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

import application.VariableTracker;
import cinema.Screening;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ScreeningAdderCtrl implements Initializable {

	
	String title = VariableTracker.movieTitle;
	Image image = VariableTracker.movieImage;
	
	@FXML
	DatePicker datePicker;
	@FXML
	ImageView poster;
	@FXML
	Label titleLbl;
	@FXML
	Label clashLbl;
	@FXML
	ComboBox<String> timesBox;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		titleLbl.setText(title);
		poster.setImage(image);
		ObservableList<String> times = FXCollections.observableArrayList();
		
		
		
		for (int i=0; i<24; i++)
		{
			String str = "";
			if(i<10)
			{
				str = "0"+i+":00";
			}
			else str = i+":00";
			times.add(str);
		}
		
		timesBox.setItems(times);
		
	}
	public void goBack(ActionEvent Event) throws IOException {
		Parent main = FXMLLoader.load(getClass().getResource("/staff/ScreeningControl.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}
	public void addScreening(ActionEvent Event) throws IOException
	{
		if (datePicker.getValue() != null && timesBox.getSelectionModel().getSelectedItem() != null)
		{

			boolean clash = false;
			String datein = datePicker.getValue().toString();
			String year = datein.substring(0, 4);
			String month = datein.substring(5, 7);
			String day = datein.substring(8, 10);
			String date = day + "/" + month + "/" + year;
			String movie = VariableTracker.movieTitle;
			String time = timesBox.getValue();
			try
			{

				JSONObject obj = new JSONObject();
				try
				{
					obj = JSONUtils.getJSONObjectFromFile("database.json");
				} catch (Exception e)
				{
					System.out.println(e);
				}
				JSONArray screenings = obj.getJSONArray("Screenings");
				for (int i = 0; i < screenings.length(); i++)
				{
					String sDate = screenings.getJSONObject(i).getString("date");
					String sTime = screenings.getJSONObject(i).getString("time");
					if (sDate.equals(date) && sTime.equals(time))
					{
						clash = true;

					}

				}

			} catch (NullPointerException e)
			{

			}
			if (!clash && checkDay(date))
			{
				Screening scr = new Screening(date, time, movie);
				Parent main = FXMLLoader.load(getClass().getResource("/staff/ScreeningControl.fxml"));
				Scene loginscene = new Scene(main);
				Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
				window.setScene(loginscene);
				window.show();
				loginscene.getWindow().centerOnScreen();

			} else if (clash)
				clashLbl.setText("Screening already scheduled at this time");
			else
				clashLbl.setText("The specific date has passed");

		}
	}
	/**
	 * This method takes as input a date and calculates if this day is in the future or in the past.
	 * @param date It returns true if the day is in the future or false if it is in the past
	 * @return This is the date as a String
	 */
	public boolean checkDay(String date)
	{
		Boolean checkCurrentDate=false;
		String movieDate="";

		String[] dayMonthYearTemp=date.split("/");
		Integer[] dayMonthYear=new Integer[3];
		
		dayMonthYear[0]=Integer.parseInt(dayMonthYearTemp[0]);
		dayMonthYear[1]=Integer.parseInt(dayMonthYearTemp[1]);
		dayMonthYear[2]=Integer.parseInt(dayMonthYearTemp[2]);
		
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
				if(now.get(Calendar.DAY_OF_MONTH)<=dayMonthYear[0])
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
		return checkCurrentDate;
	}
	
	
}
