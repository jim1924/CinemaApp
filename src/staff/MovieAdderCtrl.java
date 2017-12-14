package staff;

import java.io.IOException;
import java.io.File;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class MovieAdderCtrl.
 * @author Ahmed Afify
 */
public class MovieAdderCtrl {

	
	/** The title field. */
	@FXML
	TextField titleField;
	
	/** The description field. */
	@FXML
	TextArea descriptionField;
	
	/** The title error lbl. */
	@FXML
	Label titleErrorLbl;
	
	/** The img src lbl. */
	@FXML
	Label imgSrcLbl;
	
	/** The img error lbl. */
	@FXML
	Label imgErrorLbl;
	
	/** The description error lbl. */
	@FXML
	Label descriptionErrorLbl;
	
	
	/** The fc. */
	final FileChooser fc = new FileChooser();
	
	/** The img chosen. */
	boolean imgChosen = false;
	
	/** The img src. */
	String imgSrc;
		
	
	/**
	 * Go back.
	 *
	 * @param Event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void goBack(ActionEvent Event) throws IOException {
		Parent main = FXMLLoader.load(getClass().getResource("/staff/StaffMovieControl.fxml"));
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
	 * Adds the movie.
	 *
	 * @param Event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void addMovie(ActionEvent Event) throws IOException
	{
		boolean titleIsValid = DataValidation.emptyValidator(titleField, titleErrorLbl);
		boolean descriptionIsValid = DataValidation.emptyValidator(descriptionField, descriptionErrorLbl);
		if(!imgChosen)
		{
			imgErrorLbl.setText("Please Upload an image");
		}
		else if(imgChosen)
		{
			imgErrorLbl.setText("");
		}
		String title = titleField.getText();
		String description = descriptionField.getText();
		
		if(titleIsValid && descriptionIsValid &&imgChosen)
		{
			Movie movie = new Movie(title,description,imgSrc);
			Parent main = FXMLLoader.load(getClass().getResource("/staff/StaffMovieControl.fxml"));
			Scene loginscene = new Scene(main);
			Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
			window.setScene(loginscene);
			window.show();
			loginscene.getWindow().centerOnScreen();
			
		}
	}
	
	/**
	 * Upload file.
	 *
	 * @param Event the event
	 */
	public void uploadFile(ActionEvent Event)
	{
		Node node = (Node) Event.getSource();
		configureFileChooser(fc);
		  File file = fc.showOpenDialog(node.getScene().getWindow());
		  
          if (file != null) {
              System.out.println(file.getAbsolutePath());
              imgSrcLbl.setText(file.getAbsolutePath());
              try {
                  File destDir = new File("assets/");
                  FileUtils.copyFileToDirectory(file, destDir);
                  System.out.println("copied");
                  imgSrc="assets/"+file.getName();;
                  
                
                  System.out.println(imgSrc);
              } catch(Exception e) {
              }
              imgChosen = true;
          }
	}
	
	/**
	 * Configure file chooser.
	 *
	 * @param fileChooser the file chooser
	 */
	private static void configureFileChooser(
	       FileChooser fileChooser) {      
	            fileChooser.setTitle("Select Image");
	            fileChooser.setInitialDirectory(
	                new File(System.getProperty("user.home"))
	            );                 
	            fileChooser.getExtensionFilters().addAll(
	                new FileChooser.ExtensionFilter("All Images", "*.*"),
	                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
	                new FileChooser.ExtensionFilter("PNG", "*.png")
	            );
	    }
}
