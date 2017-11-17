package customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MovieSelectCtrl implements Initializable
{

	@FXML
	ListView<String> list = new ListView<>();
	@FXML
	Label description = new Label();
	@FXML
	ImageView iv = new ImageView();
	ObservableList<String> items = FXCollections.observableArrayList("Single", "Double", "Suite", "Family App", "Suite",
			"Family App", "Suite", "Family App", "Suite", "Family App", "Suite", "Family App");

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		list.setItems(items);
		list.setOnMouseClicked(new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent event)
			{
				System.out.println("clicked on " + list.getSelectionModel().getSelectedItem());
			}
		});
		Image image = new Image(getClass().getResourceAsStream("/images/10.jpg"));
		iv.setImage(image);
		

	}

	public void back(ActionEvent Event) throws IOException
	{
		// code to go one screen back
		Parent main = FXMLLoader.load(getClass().getResource("/customer/HomePage.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}

	public void booknow(ActionEvent Event) throws IOException
	{
		System.out.println(list.getSelectionModel().getSelectedItem());
		
		//code to go to the booking screen 

		 
	}
	public void logout(ActionEvent Event) throws IOException
	{
		// code to go one screen back
		Parent main = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}

}
