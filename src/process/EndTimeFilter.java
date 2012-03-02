package process;

import input.CalendarEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.joda.time.DateTime;

/*
 * Filters the events to be displayed in chronological order according the 
 * End DateTime. Additionally, the function can be reversed by adding reverse as the 
 * first parameter in the filter method.   
 */

public class EndTimeFilter implements Filter {

	// Name of the command to be called in Main
	private String myCommandName = "endInOrder";
	
	public String getCommandName() {
		return myCommandName;
	}

	/*
	 * Over-ridden filter method from the filter superclass. Uses a comparator
	 * to filter the Dates in chronological order by end time Takes in a
	 * parameter ("reverse" string) that returns the inverse.
	 */
	public ArrayList<CalendarEvent> filter(ArrayList<Object> parameters,
	        ArrayList<CalendarEvent> events) {

		// checks to make sure these parameters are in fact Strings
		String version = null;
		try {
			version = (String) parameters.get(0);
		} catch (ClassCastException cce) {
			System.out.println("Instance" + parameters.get(0).toString()
			        + " was not a String");
		}

		// creates instance of the comparator
		InOrderFilter myFilter = new InOrderFilter();
		ArrayList<CalendarEvent> eventsToReturn = events;

		// Puts the list in order
		Collections.sort(eventsToReturn, myFilter);

		// if the a reverse command is added, the list put in an inverse order
		if (version.equals("reverse")) {
			Collections.reverse(eventsToReturn);
		}

		return eventsToReturn;
	}

	/*
	 * comparator used by the filter method uses the calendar event class
	 * methods to find the end time of each event
	 */
	private class InOrderFilter implements Comparator<CalendarEvent> {

		public int compare(CalendarEvent o1, CalendarEvent o2) {

			DateTime start1 = o1.getMyEndTime();
			DateTime start2 = o2.getMyEndTime();

			/*
			 * uses the DateTime compareTo method to evaluate Returns -1,0, or 1
			 */
			int test = start1.compareTo(start2);
			return test;

		}

	}

}
