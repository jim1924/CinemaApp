package application;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class VariableTracker {
public static String movieTitle = "";
public static String movieDescription = "";
public static Image movieImage = null;



//Staff Details
public static String selectedMovieStaff;
public static String selectedDateStaff;
public static String selectedTimeStaff;



//Customer Details
public static String custEmail="";
public static String custFirstName="";
public static String custLastName="";
public static ArrayList<Integer> custBookings=new ArrayList<Integer>();
public static boolean selectMovieFirst=true;

public static String selectedMovie;
public static String selectedDate;
public static String selectedTime;



//public static void setMoviePosition(int i)
//{
//	moviePosition=i;
//}
//public static int getMoviePosition()
//{
//	return moviePosition;
//}
}
