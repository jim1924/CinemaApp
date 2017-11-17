package staff;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePageCtrl {
	public void goback(ActionEvent Event) throws IOException
	{
		Parent main = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		Scene mainscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(mainscene);
		window.show();
		mainscene.getWindow().centerOnScreen();
	}

}
