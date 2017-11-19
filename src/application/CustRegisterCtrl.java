package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
	
	public Customer registerCustomer(ActionEvent event)
	{
		String emailVal=email.getText();
		String pass=password.getText();
		Customer cust = new Customer(emailVal,pass);
		System.out.println(cust.getEmail());
		return cust;
	}
	
	
}
