package customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ConfirmationCtrl implements Initializable
{


	@FXML
	GridPane grid = new GridPane();

	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		

	}

	

	public void logout(ActionEvent Event) throws IOException // logout button
	{
		// code to go to the first screen
		Parent main = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}
	public void home(ActionEvent Event) throws IOException // home page button
	{
		// code to go to home
		Parent main = FXMLLoader.load(getClass().getResource("/customer/HomePage.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}

}