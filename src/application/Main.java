package application;

	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;




import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.StageStyle;

/**
 * 
 * This is where the java FX project starts
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setResizable(false); //the windows cannot be resized
			primaryStage.initStyle(StageStyle.UTILITY); //the stage cannot me minimized,maximized or resized
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		launch(args);


	}
}
