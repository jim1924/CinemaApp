package cinema;

public class Seat {

	private boolean booked;
public Seat() {
	this.booked=false;
}
public void book()
{
	booked=true;
}
public boolean getBooked()
{
	return booked;
}
}

