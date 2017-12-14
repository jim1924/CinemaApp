package customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

import application.VariableTracker;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.stage.Stage;

/**
 * This is the controller class of the seat type selection. The user here can choose types of a ticket like adult ticket, child ticket or student ticket
 * 
 * @author Dimitris Selalmazidis
 *
 */

public class SeatTypeCtrl implements Initializable
{
	@FXML
	Label adultSub=new Label();
	@FXML
	Label studentSub=new Label();
	@FXML
	Label childSub=new Label();
	@FXML
	Label total=new Label();
	
	@FXML
	ComboBox<Integer> adultNumber=new ComboBox<>();
	@FXML
	ComboBox<Integer> studentNumber=new ComboBox<>();
	@FXML
	ComboBox<Integer> childNumber=new ComboBox<>();
	
	int totalAvailableSeats;
	
	public SeatTypeCtrl() throws IOException {
	} 
	
	JSONObject obj = JSONUtils.getJSONObjectFromFile("database.json");
	JSONArray screenings = obj.getJSONArray("Screenings");
	JSONArray bookings = obj.getJSONArray("Bookings");
	JSONArray customerDetails = obj.getJSONArray("CustomerDetails");

	/**
	 * 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		ObservableList<Integer> number = FXCollections.observableArrayList(0);
		adultNumber.setItems(number);
		adultNumber.getSelectionModel().select(0);
		studentNumber.setItems(number);
		studentNumber.getSelectionModel().select(0);
		childNumber.setItems(number);
		childNumber.getSelectionModel().select(0);
		
		adultSub.setText("0");
		studentSub.setText("0");
		childSub.setText("0");
		
		for (int i=0;i<screenings.length();i++)
		{
			if (screenings.getJSONObject(i).getString("title").equals(application.VariableTracker.selectedMovie)&& screenings.getJSONObject(i).getString("date").equals(application.VariableTracker.selectedDate) && screenings.getJSONObject(i).getString("time").equals(application.VariableTracker.selectedTime))
			{
				totalAvailableSeats=screenings.getJSONObject(i).getInt("freeSeats");
			}
		}
		
		adultNumber.valueProperty().addListener(new ChangeListener<Integer>() {
	        @Override public void changed(ObservableValue ov, Integer t, Integer t1) {
	        	try{
	        		
		        	adultSub.setText(Integer.toString((Integer.parseInt((adultNumber.getSelectionModel().getSelectedItem().toString()))*8)) );
		        	int sub1= Integer.parseInt(adultSub.getText());
		        	int sub2= Integer.parseInt(studentSub.getText());
		        	int sub3= Integer.parseInt(childSub.getText());
		        	total.setText(Integer.toString((sub1+sub2+sub3)));
		        	
	        	}
	        	catch(Exception e)
	        	{
	        		
	        	}
	        }    
	    });
		studentNumber.valueProperty().addListener(new ChangeListener<Integer>() {
	        @Override public void changed(ObservableValue ov, Integer t, Integer t1) {
	        	
	        	try{
	        	studentSub.setText(Integer.toString((Integer.parseInt((studentNumber.getSelectionModel().getSelectedItem().toString()))*6)) );
	        	int sub1= Integer.parseInt(adultSub.getText());
	        	int sub2= Integer.parseInt(studentSub.getText());
	        	int sub3= Integer.parseInt(childSub.getText());
	        	total.setText(Integer.toString((sub1+sub2+sub3)));
	        	
        	}
        	catch(Exception e)
        	{
        		
        	}
	        }    
	    });
		childNumber.valueProperty().addListener(new ChangeListener<Integer>() {
	        @Override public void changed(ObservableValue ov, Integer t, Integer t1) {
	        	try{
	        	childSub.setText(Integer.toString((Integer.parseInt((childNumber.getSelectionModel().getSelectedItem().toString()))*4)) );
	        	int sub1= Integer.parseInt(adultSub.getText());
	        	int sub2= Integer.parseInt(studentSub.getText());
	        	int sub3= Integer.parseInt(childSub.getText());
	        	total.setText(Integer.toString((sub1+sub2+sub3)));
	        	}
	        	catch(Exception e)
	        	{
	        		
	        	}
	        }    
	    });


	}
	
	
	public void pupolateAdults() throws IOException
	{
		int localAvailable=totalAvailableSeats-Integer.parseInt((studentNumber.getSelectionModel().getSelectedItem()).toString())-Integer.parseInt((childNumber.getSelectionModel().getSelectedItem().toString()));
		if(localAvailable<0)
		localAvailable=0;
		ObservableList<Integer> number = FXCollections.observableArrayList();
		if (localAvailable>10)
		{
			for (int i=0;i<=10;i++)
			{
				number.add(i);
			}
			adultNumber.setItems(number);
		}
		else
		{
			for (int j=0;j<=localAvailable;j++)
			{
				number.add(j);
			}
			adultNumber.setItems(number);
		}
	}
	
	public void pupolateStudent() throws IOException
	{
		int localAvailable=totalAvailableSeats-Integer.parseInt((adultNumber.getSelectionModel().getSelectedItem().toString()))-Integer.parseInt(childNumber.getSelectionModel().getSelectedItem().toString());
		if(localAvailable<0)
		localAvailable=0;
		ObservableList<Integer> number = FXCollections.observableArrayList();
		

		if (localAvailable>10)
		{
			for (int i=0;i<=10;i++)
			{
				number.add(i);
			}
			studentNumber.setItems(number);
		}
		else
		{
			for (int j=0;j<=localAvailable;j++)
			{
				number.add(j);
			}
			studentNumber.setItems(number);
		}
		
	}
	
	public void pupolateChild() throws IOException
	{
		int localAvailable=totalAvailableSeats-Integer.parseInt((studentNumber.getSelectionModel().getSelectedItem().toString()))-Integer.parseInt((adultNumber.getSelectionModel().getSelectedItem().toString()));
		if(localAvailable<0)
		localAvailable=0;
		ObservableList<Integer> number = FXCollections.observableArrayList();
		

		if (localAvailable>10)
		{
			for (int i=0;i<=10;i++)
			{
				number.add(i);
			}
			childNumber.setItems(number);
		}
		else
		{
			for (int j=0;j<=localAvailable;j++)
			{
				number.add(j);
			}
			childNumber.setItems(number);
		}

	}
	
	/**
	 * This method loads the Seat Selection page after the user has chosen the seat-type
	 * 
	 * @param Event
	 * @throws IOException
	 */
	public void chooseSeats(ActionEvent Event) throws IOException
	{
		if(adultNumber.getSelectionModel().getSelectedItem()!=0 || childNumber.getSelectionModel().getSelectedItem()!=0 || studentNumber.getSelectionModel().getSelectedItem()!=0 )
		{
			VariableTracker.totalCost=Integer.parseInt(total.getText());
			VariableTracker.totalSeatsToBook=adultNumber.getSelectionModel().getSelectedItem()+childNumber.getSelectionModel().getSelectedItem()+studentNumber.getSelectionModel().getSelectedItem();
			System.out.println(VariableTracker.totalCost);
			System.out.println(VariableTracker.totalSeatsToBook);
		}
	}


	/**
	 * This method is linked with the logout button and logs out the user
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
	
	/**
	 * This method is linked with the back button and moves the user to the previous screen
	 * @param Event
	 * @throws IOException
	 */
	public void back(ActionEvent Event) throws IOException 
	{
		if (application.VariableTracker.selectMovieFirst)
		{
			Parent main = FXMLLoader.load(getClass().getResource("/customer/MovieSelect.fxml"));
			Scene loginscene = new Scene(main);
			Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
			window.setScene(loginscene);
			window.show();
			loginscene.getWindow().centerOnScreen();
		}
		else
		{
			Parent main = FXMLLoader.load(getClass().getResource("/customer/DateSelection.fxml"));
			Scene loginscene = new Scene(main);
			Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
			window.setScene(loginscene);
			window.show();
			loginscene.getWindow().centerOnScreen();
		}
	}
	
	
	
}