package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
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

/**
 * This is the controller of the login page. In here the customer or the
 * employee can enter their credentials and login (it checks credentials on the
 * fly) Also new customers can register
 * 
 * @author Ahmed Afify
 *
 */
public class MainCtrl implements Initializable {

	/**
	 * This method moves the user to the registration page
	 * 
	 * @param Event
	 * @throws IOException
	 */
	public void goToCustReg(ActionEvent Event) throws IOException {

		Parent reg = FXMLLoader.load(getClass().getResource("/customer/CustRegister.fxml"));
		Scene regscene = new Scene(reg);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(regscene);
		window.show();
		regscene.getWindow().centerOnScreen();
	}

	/**
	 * This method is initializing the first page of the program. The concept of
	 * readDBandAssets() method is the following. Because the jar file can only be
	 * read and not written, additional files outside of the jar file must be
	 * created. So the readBDandAssets() method is firstly trying to read the
	 * database and assets folder outside of the jar. If it succeeds then it is
	 * fine. If there is a fileNotFoundException (e.g in first execution), then it
	 * reads the jar file and copies the database and assets folder outside of the
	 * .jar file. From then on the whole application is reading and writing to the
	 * files outside of the jar.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		readDBandAssets();

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

	/**
	 * This method checks in the database for an e-mail-password match and moves to
	 * the customer page or the staff page depending on the credentials
	 * 
	 * @param Event
	 * @throws Exception
	 */

	public void checkCredentials(ActionEvent Event) throws Exception {

		boolean emailIsValid = DataValidation.emailValidator(email, emailErrorLbl);
		boolean passwordIsValid = DataValidation.passwordValidator(password, passwordErrorLbl);

		if (emailIsValid && passwordIsValid) {
			String sEmail = email.getText();
			String sPassword = password.getText();
			boolean isCustomer = false;
			boolean isStaff = false;

			JSONObject obj = JSONUtils.getJSONObjectFromFile("database.json");
			JSONArray custArray = obj.getJSONArray("CustomerDetails");
			JSONArray staffArray = obj.getJSONArray("StaffDetails");

			for (int i = 0; i < custArray.length(); i++) {
				JSONObject cust = custArray.getJSONObject(i);
				String jEmail = cust.getString("email");

				if (sEmail.equals(jEmail)) {
					String jPassword = cust.getString("password");
					if (sPassword.equals(jPassword)) {
						isCustomer = true;
						VariableTracker.custFirstName = cust.getString("firstName");
						VariableTracker.custLastName = cust.getString("lastName");
						for (int k = 0; k < custArray.getJSONObject(i).getJSONArray("bookings").length(); k++)

						{
							VariableTracker.custBookings
									.add(((custArray.getJSONObject(i).getJSONArray("bookings").getInt(k))));
						}
						break;
					}
				}
			}
			if (!isCustomer) {
				for (int i = 0; i < staffArray.length(); i++) {
					JSONObject staff = staffArray.getJSONObject(i);
					String jEmail = staff.getString("email");

					if (sEmail.equals(jEmail)) {
						String jPassword = staff.getString("password");
						if (sPassword.equals(jPassword)) {
							isStaff = true;
							break;
						}
					}
				}
			}
			if (isCustomer) {
				VariableTracker.custEmail = email.getText();

				Parent HomePage = FXMLLoader.load(getClass().getResource("/customer/HomePage.fxml"));
				Scene HomePageScene = new Scene(HomePage);
				Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
				window.setScene(HomePageScene);
				window.show();
				HomePageScene.getWindow().centerOnScreen();
			} else if (isStaff) {
				Parent HomePage = FXMLLoader.load(getClass().getResource("/staff/StaffHomePage.fxml"));
				Scene HomePageScene = new Scene(HomePage);
				Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
				window.setScene(HomePageScene);
				window.show();
				HomePageScene.getWindow().centerOnScreen();
			}

			else
				loginErrorLbl.setText("Invalid details");
		}
	}

	/**
	 * This method is called when the first screen of the app is launched. Due to
	 * the fact that .jar files cannot be edited, what this method is doing is
	 * trying to read the database from the directory of the .jar file. If there is
	 * no such file, it reads from inside the .jar and copies the database outside
	 * of the .jar file. From then on all of the app reads and writes to the files
	 * outside of the .jar file. The same procedure is followed for the assets
	 * folder.
	 */
	private void readDBandAssets() {

		try {
			JSONObject obj = JSONUtils.getJSONObjectFromFile("database.json");
			System.out.println("Database exists");

		} catch (Exception e) {

			System.out.println("Database not found");
			try {
				InputStream in = getClass().getResourceAsStream("/database.json");
				JSONObject obj = JSONUtils.getJSONObjectFromFile(in);
				FileWriter write = new FileWriter("database.json");
				write.write(obj.toString());
				write.close();
				System.out.println("New database created");

			} catch (Exception ex) {
				System.out.println("Database wasn't written");
			}
		}

		File folder = new File("assets");

		if (folder.exists() && folder.isDirectory()) {
			System.out.println("assets folder exists");
		} else {
			System.out.println("folder wasn't found.");
			new File("assets").mkdir();
			try {
				updateWithDummy();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method populates the newly created assets folder with the pre-existing
	 * movie images
	 * 
	 * @throws IOException
	 */
	private void updateWithDummy() throws IOException {
		for (int i = 1; i <= 10; i++) {
			URL inputUrl = getClass().getResource("/assets/" + i + ".jpg");
			File dest = new File("assets/" + i + ".jpg");
			FileUtils.copyURLToFile(inputUrl, dest);
		}
		URL inputUrl1 = getClass().getResource("/assets/Justice League.jpg");
		File dest1 = new File("assets/Justice League.jpg");
		FileUtils.copyURLToFile(inputUrl1, dest1);
		URL inputUrl2 = getClass().getResource("/assets/placeholder.png");
		File dest2 = new File("assets/placeholder.png");
		FileUtils.copyURLToFile(inputUrl2, dest2);
		System.out.println("New folder created");
	}

}
