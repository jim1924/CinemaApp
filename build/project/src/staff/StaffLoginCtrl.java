package staff;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

import application.DataValidation;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StaffLoginCtrl {
	// go back button
	public void goBack(ActionEvent Event) throws IOException {
		Parent main = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}
	
	@FXML
	TextField email;
	@FXML
	TextField password;
	@FXML
	Label emailErrorLbl;
	@FXML
	Label passwordErrorLbl;
	@FXML
	Label loginErrorLbl;

	// check credentials method
	public void checkCredentials(ActionEvent Event) throws IOException {
		//goes in the if afterwards
		Parent page = FXMLLoader.load(getClass().getResource("/staff/StaffHomePage.fxml"));
		Scene scene=new Scene (page);
		Stage window=(Stage)((Node)Event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
		scene.getWindow().centerOnScreen();
		
		boolean emailIsValid = DataValidation.emailValidator(email, emailErrorLbl);
		boolean passwordIsValid = DataValidation.passwordValidator(password, passwordErrorLbl);

		if (emailIsValid && passwordIsValid) {
			String sEmail = email.getText();
			String sPassword = password.getText();
			boolean validDetails = false;
			JSONObject obj = JSONUtils.getJSONObjectFromFile("./src/assets/obj.json");
			JSONArray jsonArray = obj.getJSONArray("StaffDetails");

			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject cust = jsonArray.getJSONObject(i);
				String jEmail = cust.getString("email");

				if (sEmail.equals(jEmail)) {
					String jPassword = cust.getString("password");
					if (sPassword.equals(jPassword)) {
						validDetails = true;
						break;
					}
				}
			}
			if (validDetails) {
				
				
			} else
				loginErrorLbl.setText("Invalid details");

		}

	}

}
