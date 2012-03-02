package process;

import input.CalendarEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * Filters the list of events according to their names 
 * Can be reversed by adding "reverse" string as a parameter 
 * Subclass of Filter Method
 * 
 */

public class NameOrderFilter implements Filter {

	// command name used to call this type filter
	private String myCommandName = "nameOrder";

	public String getCommandName() {
		return myCommandName;
	}

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

			/*
			 * uses the String compareTo method to evaluate Returns -1,0, or 1
			 */
			return o1.getMyName().compareTo(o2.getMyName());
		}

	}

}
