package staff;

import java.io.IOException;

import application.JsonExporter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class StaffHomePageCtrl.
 * @author Ahmed Afify
 */
public class StaffHomePageCtrl   {
	
	/** The export status lbl. */
	@FXML
	Label exportStatusLbl;
	
	/**
	 * Go back.
	 *
	 * @param Event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void goBack(ActionEvent Event) throws IOException {
		Parent main = FXMLLoader.load(getClass().getResource("/application/main.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}
	
	/**
	 * Log out.
	 *
	 * @param Event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void logOut(ActionEvent Event) throws IOException {
		Parent main = FXMLLoader.load(getClass().getResource("/application/main.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}
	
	/**
	 * Go to movie control.
	 *
	 * @param Event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void goToMovieControl(ActionEvent Event) throws IOException {
		Parent main = FXMLLoader.load(getClass().getResource("/staff/StaffMovieControl.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
		
	}
	
	/**
	 * Export CSV.
	 *
	 * @param Event the event
	 */
	public void exportCSV(ActionEvent Event)
	{
		JsonExporter exp = new JsonExporter();
		exportStatusLbl.setText(exp.getStatus());
		
	}
}
