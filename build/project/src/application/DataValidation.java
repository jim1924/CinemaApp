package application;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DataValidation {

	

	

	public static boolean emptyValidator(TextArea field, Label label) 
	{
		boolean valid = true;
		String labelString = null;
		if (field.getText().isEmpty())
		{
			valid=false;
			labelString = "Field can not be empty.";
			
		}
		
		label.setText(labelString);
		return valid;
	}
	
	public static boolean emptyValidator(TextField field, Label label) 
	{
		boolean valid = true;
		String labelString = null;
		if (field.getText().isEmpty())
		{
			valid=false;
			labelString = "Field can not be empty.";
			
		}
		
		label.setText(labelString);
		return valid;
	}
	
	public static boolean nameValidator(TextField field, Label label) 
	{
		boolean valid = true;
		String labelString = null;
		if (field.getText().isEmpty())
		{
			valid=false;
			labelString = "Name field can not be empty.";
			
		}
		else if(!field.getText().matches("[a-zA-Z]+"))
		{
			valid=false;
			labelString="Name can only contain letters.";
		}
		label.setText(labelString);
		
		return valid;
	}

	public static boolean emailValidator(TextField field, Label label) 
	{
		boolean valid = true;
		String labelString = null;
		if (field.getText().isEmpty())
		{
			valid=false;
			labelString = "Email field can not be empty.";
			
		}
		else if(!field.getText().matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$"))
		{
			valid=false;
			labelString="Please enter a valid email address.";
		}
		label.setText(labelString);
		
		return valid;
	}
	
	public static boolean passwordValidator(TextField field, Label label) 
	{
		boolean valid = true;
		String labelString = null;
		if (field.getText().isEmpty())
		{
			valid=false;
			labelString = "Password field can not be empty.";
			
		}
		else if(!field.getText().matches("^.{4,16}$"))
		{
			valid=false;
			labelString="Password must be at least 4-16 charachters long.";
		}
		label.setText(labelString);
		
		return valid;
	}
	public static boolean passwordsMatch(TextField pass1, TextField pass2, Label label)
	{
		boolean match = true;
		String labelString = null;
		if(!pass1.getText().equals(pass2.getText()))
		{
			match=false;
			labelString = "Passwords must match.";
		}
		label.setText(labelString);
		return match;
	}


	
}
