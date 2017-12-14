package application;

import java.util.ArrayList;

import javafx.scene.image.Image;

// TODO: Auto-generated Javadoc
/**
 *
 *This class stores static variables for data sharing between pages.
 *For example when the user logs in their personal details are stored here (exept password).
 *Also when the user selects a combination of movie-day-time, those information are stored here and retrieved from the SeatSelection controller
 *@author Ahmed Afify
 */
public class VariableTracker {

/** The movie title. */
public static String movieTitle = "";

/** The movie description. */
public static String movieDescription = "";

/** The movie image. */
public static Image movieImage = null;



/** The selected movie staff. */
//Staff Details
public static String selectedMovieStaff;

/** The selected date staff. */
public static String selectedDateStaff;

/** The selected time staff. */
public static String selectedTimeStaff;



/** The cust email. */
//Customer Details
public static String custEmail="";

/** The cust first name. */
public static String custFirstName="";

/** The cust last name. */
public static String custLastName="";

/** The cust bookings. */
public static ArrayList<Integer> custBookings=new ArrayList<Integer>();

/** The select movie first. */
public static boolean selectMovieFirst=true;
public static Integer totalSeatsToBook;
public static Integer totalCost;

/** The selected movie. */
public static String selectedMovie;

/** The selected date. */
public static String selectedDate;

/** The selected time. */
public static String selectedTime;

}
