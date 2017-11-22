package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * This class incorporates the login page for the Customer.
 * 
 */

public class CustLoginCtrl
{

	/**
	 * 
	 * This method is connected with the back button. When this button is clicked, it goes to the
	 * previous page
	 * 
	 * @param Event
	 * @throws IOException
	 */

	//go back method
	public void goback(ActionEvent Event) throws IOException
	{

		Parent main = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
	}

	@FXML
	TextField username = new TextField();// decleration of a username text field
	@FXML
	TextField password = new TextField();// decleration of a password text field
	@FXML
	Label lab = new Label();

	/**
	 * 
	 * @param Event
	 * @throws IOException
	 */

	public void checkcredentials(ActionEvent Event) throws IOException
	{

		lab.setVisible(false);
		BufferedReader br = new BufferedReader(new FileReader("./Credentials/Customers.txt"));
		String line = br.readLine();

		while (line != null)
		{
			String[] tester = new String[2];
			tester = line.split(",");
			// if (tester[0].equals(username.getText()) && tester[1].equals(password.getText())) {
			if (true)
			{
				// go to next scene
				Parent HomePage = FXMLLoader.load(getClass().getResource("/customer/HomePage.fxml"));
				Scene HomePageScene = new Scene(HomePage);
				Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
				window.setScene(HomePageScene);
				window.show();
				HomePageScene.getWindow().centerOnScreen();
				break;
			}
			line = br.readLine();
			if (line == null)
			{
				lab.setVisible(true);
			}
		}
		br.close();

	}

	public void register(ActionEvent Event)throws IOException{
		
		Parent HomePage = FXMLLoader.load(getClass().getResource("/customer/CustRegister.fxml"));
		Scene HomePageScene = new Scene(HomePage);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(HomePageScene);
		window.show();
		HomePageScene.getWindow().centerOnScreen();
		
		
	}
	
}
