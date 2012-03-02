package process;

import input.CalendarEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.joda.time.DateTime;

/*
 * Filters the events to be displayed in chronological order according the 
 * Start DateTime. Additionally, the function can be reversed by adding reverse as the 
 * first parameter in the filter method.   
 */

public class StartTimeFilter implements Filter {

	// Command name to be called
	private String myCommandName = "startInOrder";

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
	 * methods to find the start time of each event
	 */
	public class InOrderFilter implements Comparator<CalendarEvent> {
		public int compare(CalendarEvent o1, CalendarEvent o2) {

			// uses the start time method to get startDates
			DateTime start1 = o1.getMyStartTime();
			DateTime start2 = o2.getMyStartTime();

			// uses built in compareTo method in the DateTime class
			int test = start1.compareTo(start2);
			return test;

		}

	}

}
