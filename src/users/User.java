package users;

public class User {

protected String email="";
protected String password="";
private String firstName="";
private String lastName="";

public User (String email, String password)
{
	this.email=email;
	this.password=password;
}
public String getEmail()
{
	return email;
}
}
