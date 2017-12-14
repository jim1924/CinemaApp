package customer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

import application.DataValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * This is the controller class of the profile page. The user here can change their personal details like first name,last name, e-mail and password
 * @author Dimitris Selalmazidis
 *
 */
public class ProfileCtrl implements Initializable
{
	@FXML
	TextField firstName=new TextField();
	@FXML
	TextField lastName=new TextField();
	@FXML
	TextField email=new TextField();
	@FXML
	PasswordField newPassword=new PasswordField();
	@FXML
	PasswordField verifyNewPassword=new PasswordField();
	
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
	

	/**
	 * This method is initializing the customer-profile page. It fills the first-name, last name  and e-mail fields
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		firstName.setText(application.VariableTracker.custFirstName);
		lastName.setText(application.VariableTracker.custLastName);
		email.setText(application.VariableTracker.custEmail);
		
	}


	/**
	 * This method updates the profile of the customer and moves to the home page if they didn't change the password or goes to the login page if they did.
	 * @param Event
	 * @throws IOException
	 */
	public void updateProfile(ActionEvent Event) throws IOException
	{
		boolean fNameIsValid = DataValidation.nameValidator(firstName, firstNameErrorLbl);
		boolean lNameIsValid = DataValidation.nameValidator(lastName, lastNameErrorLbl);
		boolean emailIsValid = DataValidation.emailValidator(email, emailErrorLbl);
		if(!newPassword.getText().trim().isEmpty() || !verifyNewPassword.getText().trim().isEmpty())
		{
			boolean passwordIsValid = DataValidation.passwordValidator(newPassword, passwordErrorLbl);
			boolean passwordsMatch = DataValidation.passwordsMatch(newPassword,verifyNewPassword, passwordErrorLbl2);
			if(fNameIsValid && lNameIsValid && emailIsValid && passwordIsValid && passwordsMatch)
			{
				updateDataBaseWithPassword(firstName.getText(),lastName.getText(),email.getText(),newPassword.getText());
				// code to go to the login page
				Parent main = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
				Scene loginscene = new Scene(main);
				Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
				window.setScene(loginscene);
				window.show();
				loginscene.getWindow().centerOnScreen();
			}

			
		}
		else
		{
			if(fNameIsValid && lNameIsValid && emailIsValid )
			{
				updateDataBase(firstName.getText(),lastName.getText(),email.getText());
				// code to go to the home page
				Parent main = FXMLLoader.load(getClass().getResource("/customer/HomePage.fxml"));
				Scene loginscene = new Scene(main);
				Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
				window.setScene(loginscene);
				window.show();
				loginscene.getWindow().centerOnScreen();
				
			}

		}
	}

	



	
	/**
	 * This method takes as parameters the new first name,last Name,email,password and stores them to the database and the class "variableTracker"
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param newPassword
	 * @throws IOException
	 */
	private void updateDataBaseWithPassword(String firstName, String lastName, String email, String newPassword) throws IOException
	{
		JSONObject obj = JSONUtils.getJSONObjectFromFile("database.json");
		JSONArray customerDetails = obj.getJSONArray("CustomerDetails");
		for (int i=0;i<customerDetails.length();i++)
		{
			if (customerDetails.getJSONObject(i).getString("email").equals(application.VariableTracker.custEmail))
			{
				customerDetails.getJSONObject(i).put("email", email);
				customerDetails.getJSONObject(i).put("firstName", firstName);
				customerDetails.getJSONObject(i).put("lastName", lastName);
				customerDetails.getJSONObject(i).put("password", newPassword);
			}
		}
		FileWriter write = new FileWriter( "database.json");
		write.write(obj.toString());
		write.close();
		application.VariableTracker.custEmail=email;
		application.VariableTracker.custFirstName=firstName;
		application.VariableTracker.custLastName=lastName;
	}


	/**
	 * This method takes as parameters the new first name,last Name,email and stores them to the file and the class "variableTracker"
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @throws IOException
	 */
	private void updateDataBase(String firstName, String lastName, String email) throws IOException
	{
		JSONObject obj = JSONUtils.getJSONObjectFromFile("database.json");
		JSONArray customerDetails = obj.getJSONArray("CustomerDetails");
		JSONArray Bookings = obj.getJSONArray("Bookings");
		for (int i=0;i<customerDetails.length();i++)
		{
			if (customerDetails.getJSONObject(i).getString("email").equals(application.VariableTracker.custEmail))
			{
				customerDetails.getJSONObject(i).put("email", email);
				customerDetails.getJSONObject(i).put("firstName", firstName);
				customerDetails.getJSONObject(i).put("lastName", lastName);
			}
		}
		for (int i=0;i<Bookings.length();i++)
		{
			if (Bookings.getJSONObject(i).getString("customerEmail").equals(application.VariableTracker.custEmail))
			{
				Bookings.getJSONObject(i).put("customerEmail", email);
			}
		}
		FileWriter write = new FileWriter( "database.json");
		write.write(obj.toString());
		write.close();
		application.VariableTracker.custEmail=email;
		application.VariableTracker.custFirstName=firstName;
		application.VariableTracker.custLastName=lastName;
		
	}


	/**
	 * This method is linked with the logout button and logs out the user
	 * @param Event
	 * @throws IOException
	 */
	public void logout(ActionEvent Event) throws IOException
	{
		// code to go to the first screen
		Parent main = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}
	
	/**
	 * This method is linked with the back button and moves the user to the previous screen
	 * @param Event
	 * @throws IOException
	 */
	public void back(ActionEvent Event) throws IOException // back button
	{

		Parent main = FXMLLoader.load(getClass().getResource("/customer/HomePage.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}





	
	
	
	


	
}
