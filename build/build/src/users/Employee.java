package users;

import cinema.Movie;
import cinema.Screening;

public class Employee extends User{

	public Employee(String firstName,String lastName,String email, String password) {
		super(firstName,lastName,email, password);
		// TODO Auto-generated constructor stub
	}

//	public Screening createScreening(String title,String desc, String imgSrc,int date, int time)
//	{
//		Movie movie= new Movie(title,desc);
//		Screening screening = new Screening(date,time,movie);
//		return screening;
//		
//	}

}
