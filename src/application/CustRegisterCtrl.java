package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import users.Customer;

public class CustRegisterCtrl {

	@FXML
	TextField email = new TextField();
	@FXML
	TextField password = new TextField();
	@FXML
	TextField name = new TextField();
	@FXML
	Button submit = new Button();
	
	public Customer registerCustomer(ActionEvent Event)
	{
		String emailVal=email.getText();
		String pass=password.getText();
		Customer cust = new Customer(emailVal,pass);
		System.out.println(cust.getEmail());
		
		Parent homePage = null;
		try {
			homePage = FXMLLoader.load(getClass().getResource("/customer/HomePage.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene movieSelectScene = new Scene(homePage);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(movieSelectScene);
		window.show();
		movieSelectScene.getWindow().centerOnScreen();
		return cust;
	}
	
	
}
