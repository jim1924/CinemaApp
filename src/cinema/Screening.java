package cinema;

public class Screening {
private int date;
private int time;
private Movie movie;
private Seat[][] theater = new Seat[10][10];

public Screening(int date, int time, Movie movie)
{
	this.date=date;
	this.time=time;
	this.movie=movie;
	
	for (int i=0; i<10; i++)
	{
		for (int j=0; j<10; j++)
		{
			theater[i][j]=new Seat();
		}
	}
	
	
}
public Seat getSeat(int x,int y)
{
	return theater[x][y];
	
}
}
