package staff;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
		try{
			
			String datein = datePicker.getValue().toString();
			String year = datein.substring(0,4);
			String month = datein.substring(5,7);
			String day = datein.substring(8,10);
			String date = day+"/"+month+"/"+year;
			String movie = VariableTracker.movieTitle;
			String time = timesBox.getValue();
			Screening scr = new Screening(date,time,movie);
		}
		catch (NullPointerException e)
		{
			
		}
		Parent main = FXMLLoader.load(getClass().getResource("/staff/ScreeningControl.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
		
	}
	
	
	
}
