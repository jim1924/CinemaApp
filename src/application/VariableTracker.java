package application;

import java.util.ArrayList;

import javafx.scene.image.Image;

/**
 *
 *This class stores static variables for data sharing between pages.
 *For example when the user logs in their personal details are stored here (exept password).
 *Also when the user selects a combination of movie-day-time, those information are stored here and retrieved from the SeatSelection controller
 */
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
public static Integer totalSeatsToBook;
public static Integer totalCost;

public static String selectedMovie;
public static String selectedDate;
public static String selectedTime;

}
