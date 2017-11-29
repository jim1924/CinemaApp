package customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SeatSelectionCtrl implements Initializable {

	@FXML
	GridPane grid = new GridPane();

	@Override
	public void initialize(URL location, ResourceBundle resources) // values when the screen loads
	{
		grid.setVgap(5);
		grid.setHgap(5);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				grid.setGridLinesVisible(false);
				Button button = new Button();
				button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				button.setAlignment(Pos.CENTER);
				// button.setStyle("-fx-background-color: green;");
				GridPane.setConstraints(button, i, j);
				grid.getChildren().add(button);
			}
		}

		for (Node element : grid.getChildren()) {
			element.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {

					System.out.println("Row: " + GridPane.getRowIndex(element));
					System.out.println("Column: " + GridPane.getColumnIndex(element));
				}
			});
		}

	}

	public void back(ActionEvent Event) throws IOException // back button
	{

		Parent main = FXMLLoader.load(getClass().getResource("/customer/MovieSelect.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}

	public void booknow(ActionEvent Event) throws IOException // book now button
	{

		// code to go to the booking screen
		Parent availableTimes = FXMLLoader.load(getClass().getResource("/customer/Confirmation.fxml"));
		Scene availableTimesScene = new Scene(availableTimes);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(availableTimesScene);
		window.show();
		availableTimesScene.getWindow().centerOnScreen();

	}

	public void logout(ActionEvent Event) throws IOException // logout button
	{
		// code to go to the first screen
		Parent main = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		Scene loginscene = new Scene(main);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}

}