package customer;

import java.io.IOException;

import application.DataValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Customer;

public class CustRegisterCtrl {

	@FXML
	TextField email;
	@FXML
	TextField password;
	@FXML
	TextField password2;
	@FXML
	TextField firstName;
	@FXML
	TextField lastName;
	@FXML
	Button submit;
	@FXML
	Label firstNameErrorLbl;
	@FXML
	Label lastNameErrorLbl;
	@FXML
	Label emailErrorLbl;
	@FXML
	Label passwordErrorLbl;
	@FXML
	Label passwordErrorLbl2;
	
	public void goBack(ActionEvent Event) throws IOException
	{
		Parent main = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
	}
	
	public void registerCustomer(ActionEvent Event)
	{
		String sFirstName=firstName.getText();
		String sLastName=lastName.getText();
		String sEmail=email.getText();
		String sPassword=password.getText();
		
		
		boolean fNameIsValid = DataValidation.nameValidator(firstName, firstNameErrorLbl);
		boolean lNameIsValid = DataValidation.nameValidator(lastName, lastNameErrorLbl);
		boolean emailIsValid = DataValidation.emailValidator(email, emailErrorLbl);
		boolean passwordIsValid = DataValidation.passwordValidator(password, passwordErrorLbl);
		boolean passwordsMatch = DataValidation.passwordsMatch(password, password2, passwordErrorLbl2);
		
		//boolean emailNull= DataValidation.textFieldIsNull(email, emailErrorLbl, "Email field can not be empty.");
		//boolean passwordNull= DataValidation.textFieldIsNull(password, passwordErrorLbl, "Password field can not be empty.");
		
		if(fNameIsValid && lNameIsValid && emailIsValid && passwordIsValid && passwordsMatch)
		{
		
		Customer cust = new Customer(sFirstName,sLastName,sEmail,sPassword);
		
		
//		Parent homePage = null;
//		try {
//			homePage = FXMLLoader.load(getClass().getResource("/customer/HomePage.fxml"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Scene movieSelectScene = new Scene(homePage);
//		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
//		window.setScene(movieSelectScene);
//		window.show();
//		movieSelectScene.getWindow().centerOnScreen();
		}
		
		
		
	}
	
	
}
