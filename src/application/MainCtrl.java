package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;
import cinema.*;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainCtrl implements Initializable
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
		
		Parent login = FXMLLoader.load(getClass().getResource("/application/CustLogin.fxml"));
		FadeTransition ft = new FadeTransition(Duration.millis(500), login);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
		Scene loginscene=new Scene (login);
		Stage window=(Stage)((Node)Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

		
		
	}
	
		
		
			
		
		
		
		
	

}
