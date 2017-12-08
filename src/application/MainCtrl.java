package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

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

public class MainCtrl implements Initializable
{
	public void gotostafflogin(ActionEvent Event) throws IOException{
		
		Parent login = FXMLLoader.load(getClass().getResource("/staff/StaffLogin.fxml"));
		Scene loginscene=new Scene (login);
		Stage window=(Stage)((Node)Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}
	
	public void goToCustReg(ActionEvent Event) throws IOException{
		
		Parent reg = FXMLLoader.load(getClass().getResource("/customer/CustRegister.fxml"));
		Scene regscene=new Scene (reg);
		Stage window=(Stage)((Node)Event.getSource()).getScene().getWindow();
		window.setScene(regscene);
		window.show();
		regscene.getWindow().centerOnScreen();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{

		
		
	}
	
	@FXML
	TextField email; // decleration of an email text field
	@FXML
	TextField password; // decleration of a password text field
	@FXML
	Label emailErrorLbl;
	@FXML
	Label passwordErrorLbl;
	@FXML
	Label loginErrorLbl;
	
	
	public void checkCredentials(ActionEvent Event) throws Exception
	{
/*		//i wrote this code in order to move from one screen to another without having to put every time the credentials
		if(true) {
			
	    Parent HomePage = FXMLLoader.load(getClass().getResource("/customer/HomePage.fxml"));
		Scene HomePageScene = new Scene(HomePage);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(HomePageScene);
		window.show();
		HomePageScene.getWindow().centerOnScreen();
		}*/
		
		
		boolean emailIsValid = DataValidation.emailValidator(email, emailErrorLbl);
		boolean passwordIsValid = DataValidation.passwordValidator(password, passwordErrorLbl);
		



			if (emailIsValid && passwordIsValid)
			{
				String sEmail = email.getText();
				String sPassword = password.getText();
				boolean isCustomer = false;
				boolean isStaff = false;
				
				JSONObject obj = JSONUtils.getJSONObjectFromFile("./src/assets/obj.json");
				JSONArray custArray = obj.getJSONArray("CustomerDetails");
				JSONArray staffArray = obj.getJSONArray("StaffDetails");
				
				for (int i=0; i<custArray.length(); i++)
				{
					JSONObject cust = custArray.getJSONObject(i);
					String jEmail = cust.getString("email");
					
					if(sEmail.equals(jEmail))
					{
						String jPassword = cust.getString("password");
						if(sPassword.equals(jPassword))
						{
							isCustomer = true;
							VariableTracker.custFirstName=cust.getString("firstName");
							VariableTracker.custLastName=cust.getString("lastName");
							for(int k=0;k<custArray.getJSONObject(i).getJSONArray("bookings").length();k++)
								
									{
										VariableTracker.custBookings.add(((custArray.getJSONObject(i).getJSONArray("bookings").getInt(k))));
									}
							break;
						}
					}
				}
				if(!isCustomer)
				{
					for (int i=0; i<staffArray.length(); i++)
					{
						JSONObject staff = staffArray.getJSONObject(i);
						String jEmail = staff.getString("email");
						
						if(sEmail.equals(jEmail))
						{
							String jPassword = staff.getString("password");
							if(sPassword.equals(jPassword))
							{
								isStaff = true;
								break;
							}
						}
					}
				}
				if(isCustomer)
				{
					VariableTracker.custEmail=email.getText();
					
					Parent HomePage = FXMLLoader.load(getClass().getResource("/customer/HomePage.fxml"));
					Scene HomePageScene = new Scene(HomePage);
					Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
					window.setScene(HomePageScene);
					window.show();
					HomePageScene.getWindow().centerOnScreen();
				}
				else if(isStaff)
				{
					Parent HomePage = FXMLLoader.load(getClass().getResource("/staff/StaffHomePage.fxml"));
					Scene HomePageScene = new Scene(HomePage);
					Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
					window.setScene(HomePageScene);
					window.show();
					HomePageScene.getWindow().centerOnScreen();
				}
				
				else loginErrorLbl.setText("Invalid details");

				

			}

	}
	
		
		
			
		
		
		
		
	

}
