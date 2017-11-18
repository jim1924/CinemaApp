package application;
	
import cinema.Screening;
import cinema.Seat;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import users.Customer;
import users.Employee;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//launch(args);
		Employee emp = new Employee("a@gmail.com","test");
		Screening screening1=emp.createScreening("Justice League", "Batman.Superman, and Wonder woman", "//..", 4, 3);
		Seat seat= screening1.getSeat(5, 5);
		seat.book();
		
		System.out.println(seat.getBooked());
	}
}
