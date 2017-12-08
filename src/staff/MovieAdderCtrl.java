package staff;

import java.io.IOException;

import application.DataValidation;
import cinema.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MovieAdderCtrl {

	
	@FXML
	TextField titleField;
	@FXML
	TextArea descriptionField;
	@FXML
	Label titleErrorLbl;
	@FXML
	Label descriptionErrorLbl;
	
	
	
	
	public void goBack(ActionEvent Event) throws IOException {
		Parent main = FXMLLoader.load(getClass().getResource("/staff/StaffMovieControl.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}
	
	public void addMovie(ActionEvent Event) throws IOException
	{
		boolean titleIsValid = DataValidation.emptyValidator(titleField, titleErrorLbl);
		boolean descriptionIsValid = DataValidation.emptyValidator(descriptionField, descriptionErrorLbl);
		
		String title = titleField.getText();
		String description = descriptionField.getText();
		
		if(titleIsValid && descriptionIsValid)
		{
			Movie movie = new Movie(title,description);
			Parent main = FXMLLoader.load(getClass().getResource("/staff/StaffMovieControl.fxml"));
			Scene loginscene = new Scene(main);
			Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
			window.setScene(loginscene);
			window.show();
			loginscene.getWindow().centerOnScreen();
			
		}
	}
	
}
