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


// TODO: Auto-generated Javadoc
/**
 * This is the controller class of the seat type selection. The user here can choose types of a ticket like adult ticket, child ticket or student ticket
 * 
 * @author Dimitris Selalmazidis
 *
 */

public class SeatTypeCtrl implements Initializable
{
	
	/** The adult sub. */
	@FXML
	Label adultSub=new Label();
	
	/** The student sub. */
	@FXML
	Label studentSub=new Label();
	
	/** The child sub. */
	@FXML
	Label childSub=new Label();
	
	/** The total. */
	@FXML
	Label total=new Label();
	
	/** The adult number. */
	@FXML
	ComboBox<Integer> adultNumber=new ComboBox<>();
	
	/** The student number. */
	@FXML
	ComboBox<Integer> studentNumber=new ComboBox<>();
	
	/** The child number. */
	@FXML
	ComboBox<Integer> childNumber=new ComboBox<>();
	
	/** The sub A. */
	int subA =0;
	
	/** The sub S. */
	int subS =0;
	
	/** The sub C. */
	int subC =0;
	
	/** The total cost. */
	int totalCost=0;
	
	/** The total available seats. */
	int totalAvailableSeats;
	
	/**
	 * Instantiates a new seat type ctrl.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public SeatTypeCtrl() throws IOException {
	} 
	
	/** The obj. */
	JSONObject obj = JSONUtils.getJSONObjectFromFile("database.json");
	
	/** The screenings. */
	JSONArray screenings = obj.getJSONArray("Screenings");
	
	/** The bookings. */
	JSONArray bookings = obj.getJSONArray("Bookings");
	
	/** The customer details. */
	JSONArray customerDetails = obj.getJSONArray("CustomerDetails");

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
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
	        		subA=adultNumber.getSelectionModel().getSelectedItem()*8;
		        	adultSub.setText("£"+subA );
		        	totalCost=subA+subS+subC;
		        	total.setText("£"+totalCost);
		        	
	        	}
	        	catch(Exception e)
	        	{
	        		
	        	}
	        }    
	    });
		studentNumber.valueProperty().addListener(new ChangeListener<Integer>() {
	        @Override public void changed(ObservableValue ov, Integer t, Integer t1) {
	        	
	        	try{
	        		subS=studentNumber.getSelectionModel().getSelectedItem()*6;
		        	studentSub.setText("£"+subS );
		        	totalCost=subA+subS+subC;
		        	total.setText("£"+totalCost);
	        	
        	}
        	catch(Exception e)
        	{
        		
        	}
	        }    
	    });
		childNumber.valueProperty().addListener(new ChangeListener<Integer>() {
	        @Override public void changed(ObservableValue ov, Integer t, Integer t1) {
	        	try{
	        		subC=childNumber.getSelectionModel().getSelectedItem()*4;
		        	childSub.setText("£"+subC );
		        	totalCost=subA+subS+subC;
		        	total.setText("£"+totalCost);
	        	}
	        	catch(Exception e)
	        	{
	        		
	        	}
	        }    
	    });


	}
	
	
	/**
	 * Populate adults.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void populateAdults() throws IOException
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
	
	/**
	 * Populate student.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void populateStudent() throws IOException
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
	
	/**
	 * Populate child.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void populateChild() throws IOException
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
	 * This method loads the Seat Selection page after the user has chosen the seat-type.
	 *
	 * @param Event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void chooseSeats(ActionEvent Event) throws IOException
	{
		if(adultNumber.getSelectionModel().getSelectedItem()!=0 || childNumber.getSelectionModel().getSelectedItem()!=0 || studentNumber.getSelectionModel().getSelectedItem()!=0 )
		{
			VariableTracker.totalCost=totalCost;
			VariableTracker.totalSeatsToBook=adultNumber.getSelectionModel().getSelectedItem()+childNumber.getSelectionModel().getSelectedItem()+studentNumber.getSelectionModel().getSelectedItem();
			Parent main = FXMLLoader.load(getClass().getResource("/customer/SeatSelection.fxml"));
			Scene loginscene = new Scene(main);
			Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
			window.setScene(loginscene);
			window.show();
			loginscene.getWindow().centerOnScreen();
		}
	}


	/**
	 * This method is linked with the logout button and logs out the user.
	 *
	 * @param Event the event
	 * @throws IOException Signals that an I/O exception has occurred.
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
	 * This method is linked with the back button and moves the user to the previous screen.
	 *
	 * @param Event the event
	 * @throws IOException Signals that an I/O exception has occurred.
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