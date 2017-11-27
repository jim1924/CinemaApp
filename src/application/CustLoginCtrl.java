package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.*;

/**
 * This class incorporates the login page for the Customer.
 * 
 */

public class CustLoginCtrl
{

	/**
	 * 
	 * This method is connected with the back button. When this button is clicked, it goes to the
	 * previous page
	 * 
	 * @param Event
	 * @throws IOException
	 */

	//go back method
	public void goback(ActionEvent Event) throws IOException
	{

		Parent main = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
	}

	@FXML
	TextField username = new TextField();// decleration of a username text field
	@FXML
	TextField password = new TextField();// decleration of a password text field
	@FXML
	Label lab = new Label();

	/**
	 * 
	 * @param Event
	 * @throws IOException
	 */

	public void checkcredentials(ActionEvent Event) throws IOException
	{

		lab.setVisible(true);


			if (true)
			{
				// go to next scene
				Parent HomePage = FXMLLoader.load(getClass().getResource("/customer/HomePage.fxml"));
				Scene HomePageScene = new Scene(HomePage);
				Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
				window.setScene(HomePageScene);
				window.show();
				HomePageScene.getWindow().centerOnScreen();
				

				

			}

	}

	public void register(ActionEvent Event)throws IOException{
		
		Parent HomePage = FXMLLoader.load(getClass().getResource("/application/CustRegister.fxml"));
		Scene HomePageScene = new Scene(HomePage);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(HomePageScene);
		window.show();
		HomePageScene.getWindow().centerOnScreen();
		
		
	}
	
}
