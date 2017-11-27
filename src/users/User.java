package users;

public class User {

protected String email="";
protected String password="";
protected String firstName="";
protected String lastName="";

public User (String firstName,String lastName,String email, String password)
{
	this.email=email;
	this.password=password;
	this.firstName=firstName;
	this.lastName=lastName;
	
}
public String getEmail()
{
	return email;
}
}
