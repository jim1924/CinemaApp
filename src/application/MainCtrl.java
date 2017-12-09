package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.tcg.json.JSONUtils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainCtrl implements Initializable
{
	public void gotostafflogin(ActionEvent Event) throws IOException{
		
		Parent login = FXMLLoader.load(getClass().getResource("/staff/StaffLogin.fxml"));
		Scene loginscene=new Scene (login);
		Stage window=(Stage)((Node)Event.getSource()).getScene().getWindow();
		window.setScene(loginscene);
		window.show();
		loginscene.getWindow().centerOnScreen();
	}
	
	public void goToCustReg(ActionEvent Event) throws IOException{
		
		Parent reg = FXMLLoader.load(getClass().getResource("/customer/CustRegister.fxml"));
		Scene regscene=new Scene (reg);
		Stage window=(Stage)((Node)Event.getSource()).getScene().getWindow();
		window.setScene(regscene);
		window.show();
		regscene.getWindow().centerOnScreen();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		
		try{
			

			

			
			
			
			
/*			final String path = "skata/assets";
			final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());

			if(jarFile.isFile()) {  // Run with JAR file
			    final JarFile jar = new JarFile(jarFile);
			    final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
			    while(entries.hasMoreElements()) {
			        final String name = entries.nextElement().getName();
			        if (name.startsWith(path + "/")) { //filter according to the path
			            System.out.println(name);
			        }
			    }
			    jar.close();
			} else { // Run with IDE
			    final URL url = Launcher.class.getResource("/" + path);
			    if (url != null) {
			        try {
			            final File apps = new File(url.toURI());
			            for (File app : apps.listFiles()) {
			                System.out.println(app);
			            }
			        } catch (URISyntaxException ex) {
			            // never happens
			        }
			    }
			}*/
			
			
			
		
/*		URL res = getClass().getResource("/assets/database.json");
		File f=(FileUtils.toFile(res));
		//File fa = new File(this.getClass().getResource("/assets").getPath());
		FileUtils.copyDirectory(f, new File("skata.json"));
		System.out.println("goooood");
		
		URL inputUrl = getClass().getResource("/assets");
		File dest = new File("skata");
		FileUtils.copyURLToFile(inputUrl, dest);*/


			
			
			
			
			
		JSONObject obj = JSONUtils.getJSONObjectFromFile("database.json");
		System.out.println("Database exists");

		}
		catch (Exception e){
			
			
			System.out.println("Database not found");
			try{
				InputStream in = getClass().getResourceAsStream("/database.json"); 
				JSONObject obj = JSONUtils.getJSONObjectFromFile(in);
				FileWriter write = new FileWriter( "database.json");
				write.write(obj.toString());
				write.close();
				System.out.println("New database created");
				
			}
			catch(Exception ex)
			{
				System.out.println("Database wasn't written");
			}
		}
		
			File folder = new File("assets");

			if (folder.exists() && folder.isDirectory()) {
				System.out.println("assets folder exists");
				}
			else{
				System.out.println("folder wasn't found.");
				new File("assets").mkdir();
				try
				{
					updateWithDummy();
				} catch (IOException e)
				{
					e.printStackTrace();
				}


				
			}
			
		
		
		
		
	}
	


	@FXML
	TextField email; // decleration of an email text field
	@FXML
	TextField password; // decleration of a password text field
	@FXML
	Label emailErrorLbl;
	@FXML
	Label passwordErrorLbl;
	@FXML
	Label loginErrorLbl;
	
	
	public void checkCredentials(ActionEvent Event) throws Exception
	{
/*		//i wrote this code in order to move from one screen to another without having to put every time the credentials
		if(true) {
			
	    Parent HomePage = FXMLLoader.load(getClass().getResource("/customer/HomePage.fxml"));
		Scene HomePageScene = new Scene(HomePage);
		Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
		window.setScene(HomePageScene);
		window.show();
		HomePageScene.getWindow().centerOnScreen();
		}*/
		
		
		boolean emailIsValid = DataValidation.emailValidator(email, emailErrorLbl);
		boolean passwordIsValid = DataValidation.passwordValidator(password, passwordErrorLbl);
		



			if (emailIsValid && passwordIsValid)
			{
				String sEmail = email.getText();
				String sPassword = password.getText();
				boolean isCustomer = false;
				boolean isStaff = false;
				
				JSONObject obj = JSONUtils.getJSONObjectFromFile("database.json");
				JSONArray custArray = obj.getJSONArray("CustomerDetails");
				JSONArray staffArray = obj.getJSONArray("StaffDetails");
				
				for (int i=0; i<custArray.length(); i++)
				{
					JSONObject cust = custArray.getJSONObject(i);
					String jEmail = cust.getString("email");
					
					if(sEmail.equals(jEmail))
					{
						String jPassword = cust.getString("password");
						if(sPassword.equals(jPassword))
						{
							isCustomer = true;
							VariableTracker.custFirstName=cust.getString("firstName");
							VariableTracker.custLastName=cust.getString("lastName");
							for(int k=0;k<custArray.getJSONObject(i).getJSONArray("bookings").length();k++)
								
									{
										VariableTracker.custBookings.add(((custArray.getJSONObject(i).getJSONArray("bookings").getInt(k))));
									}
							break;
						}
					}
				}
				if(!isCustomer)
				{
					for (int i=0; i<staffArray.length(); i++)
					{
						JSONObject staff = staffArray.getJSONObject(i);
						String jEmail = staff.getString("email");
						
						if(sEmail.equals(jEmail))
						{
							String jPassword = staff.getString("password");
							if(sPassword.equals(jPassword))
							{
								isStaff = true;
								break;
							}
						}
					}
				}
				if(isCustomer)
				{
					VariableTracker.custEmail=email.getText();
					
					Parent HomePage = FXMLLoader.load(getClass().getResource("/customer/HomePage.fxml"));
					Scene HomePageScene = new Scene(HomePage);
					Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
					window.setScene(HomePageScene);
					window.show();
					HomePageScene.getWindow().centerOnScreen();
				}
				else if(isStaff)
				{
					Parent HomePage = FXMLLoader.load(getClass().getResource("/staff/StaffHomePage.fxml"));
					Scene HomePageScene = new Scene(HomePage);
					Stage window = (Stage) ((Node) Event.getSource()).getScene().getWindow();
					window.setScene(HomePageScene);
					window.show();
					HomePageScene.getWindow().centerOnScreen();
				}
				
				else loginErrorLbl.setText("Invalid details");

				

			}

	}
	
	private void updateWithDummy() throws IOException
	{
		for (int i=1;i<=10;i++)
		{
			URL inputUrl = getClass().getResource("/assets/"+i+".jpg");
			File dest = new File("assets/"+i+".jpg");	
			FileUtils.copyURLToFile(inputUrl, dest);
		}
		URL inputUrl1 = getClass().getResource("/assets/Justice League.jpg");
		File dest1 = new File("assets/Justice League.jpg");	
		FileUtils.copyURLToFile(inputUrl1, dest1);
		URL inputUrl2 = getClass().getResource("/assets/placeholder.png");
		File dest2 = new File("assets/placeholder.png");	
		FileUtils.copyURLToFile(inputUrl2, dest2);
		System.out.println("New folder created");
	}
	
		
		
			
		
		
		
		
	

}
