package application;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController
{
	@FXML
	private Label myMessage;
	public void generateRandom(ActionEvent Event){
		Random rand=new Random();
		int myrand=rand.nextInt(50);
		System.out.println(myrand);
		myMessage.setText(Integer.toString(myrand));
		
		
	}

}
