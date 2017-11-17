package customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePageCtrl implements Initializable {
	public void logout(ActionEvent Event) throws IOException
	{
		//code to return to the first 
		Parent main = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}
	public void goToMovieSelect(ActionEvent Event) throws IOException
	{
		//code to return to the first 
		Parent movieSelect = FXMLLoader.load(getClass().getResource("/customer/MovieSelect.fxml"));
		Scene movieSelectScene = new Scene(movieSelect);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(movieSelectScene);
		window.show();
		movieSelectScene.getWindow().centerOnScreen();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{

		
	}
	
	

}
