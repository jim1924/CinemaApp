package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainController
{
	@FXML
	Label lab=new Label();
	public void gotosecondscene(ActionEvent Event) throws IOException{
		lab.setText("skata");
		
		Parent login = FXMLLoader.load(getClass().getResource("/application/Credentials.fxml"));
		Scene loginscene=new Scene (login);
		
		Stage window=(Stage)((Node)Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		
		
		
		
/*		Parent credentials = FXMLLoader.load(getClass().getResource("/application/Credentials.fxml"));
		Scene credentialsscene=new Scene(credentials);
		Stage app_stage=(Stage) ((Node) Event.getSource()).getScene().getWindow();
		app_stage.show();*/
			
		
		
		
		
	}

}
