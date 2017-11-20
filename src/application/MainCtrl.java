package application;

import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainCtrl
{
	public void gotostafflogin(ActionEvent Event) throws IOException{
		
		Parent login = FXMLLoader.load(getClass().getResource("/application/StaffLogin.fxml"));
		FadeTransition ft = new FadeTransition(Duration.millis(500), login);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
		Scene loginscene=new Scene (login);
		Stage window=(Stage)((Node)Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
	}
	
	public void gotocustlogin(ActionEvent Event) throws IOException{
		
		Parent login = FXMLLoader.load(getClass().getResource("/application/CustRegister.fxml"));
		FadeTransition ft = new FadeTransition(Duration.millis(500), login);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
		Scene loginscene=new Scene (login);
		Stage window=(Stage)((Node)Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
	}
	
		
		
			
		
		
		
		
	

}
