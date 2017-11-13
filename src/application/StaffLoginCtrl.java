package application;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class StaffLoginCtrl
{

	public void goback(ActionEvent Event) throws IOException{
		
		Parent main = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		Scene loginscene=new Scene (main);
		Stage window=(Stage)((Node)Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
	}
	
	public void checkcredentials(ActionEvent Event) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Dimitris Selal\\workspace\\CinemaApp\\Credentials\\Staff.txt"));
		int count=0;
        while (br.readLine() != null) {
            count=count+1;
        }
        System.out.println(count);
	
	}

}

