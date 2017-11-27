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
import javafx.scene.control.TextField;
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
	
	public void goToCustReg(ActionEvent Event) throws IOException{
		
		Parent reg = FXMLLoader.load(getClass().getResource("/application/CustRegister.fxml"));
		FadeTransition ft = new FadeTransition(Duration.millis(500), reg);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
		Scene regscene=new Scene (reg);
		Stage window=(Stage)((Node)Event.getSource()).getScene().getWindow();
		window.setScene(regscene);
		window.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

		
		
	}
	
	@FXML
	TextField email; // decleration of a email text field
	@FXML
	TextField password; // decleration of a password text field
	@FXML
	Label emailErrorLbl;
	@FXML
	Label passwordErrorLbl;
	
	
	public void checkcredentials(ActionEvent Event) throws IOException
	{
		boolean emailIsValid = DataValidation.emailValidator(email, emailErrorLbl);
		boolean passwordIsValid = DataValidation.passwordValidator(password, passwordErrorLbl);



			if (emailIsValid && passwordIsValid)
			{
//				// go to next scene
//				Parent HomePage = FXMLLoader.load(getClass().getResource("/customer/HomePage.fxml"));
//				Scene HomePageScene = new Scene(HomePage);
//				Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
//				window.setScene(HomePageScene);
//				window.show();
//				HomePageScene.getWindow().centerOnScreen();
				System.out.println("ok");

				

			}

	}
	
		
		
			
		
		
		
		
	

}
