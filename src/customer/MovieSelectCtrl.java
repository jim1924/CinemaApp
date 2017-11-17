package customer;

import java.io.IOException;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class MovieSelectCtrl {
	
	
	@FXML
	ListView<String> list = new ListView<>();
	ObservableList<String> items =FXCollections.observableArrayList (
		    "Single", "Double", "Suite", "Family App");
	
	public void logout(ActionEvent Event) throws IOException
	{
/*		//code to go one screen back
		Parent main = FXMLLoader.load(getClass().getResource("/customer/HomePage.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();*/
		populate();
	}
	public void populate(){
		list.setItems(items);
		list.setPrefSize(300, 300);
	}
	
	

}
