package users;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Customer extends User{

	public Customer(String email, String password) {
		super(email, password);
		registerCustomer();
	}
	public void registerCustomer()
	{
		final String FILENAME = "./Credentials/Customers.txt";
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			String content = email+","+password+"\n";

			fw = new FileWriter(FILENAME,true);
			bw = new BufferedWriter(fw);
			bw.write(content);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

		
	}

}
