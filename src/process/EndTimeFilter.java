package process;

import input.CalendarEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.joda.time.DateTime;

/*
 *  Filter Events in the order of event end time
 */
public class EndTimeFilter implements Filter {

	private String myCommandName = "end in order";

	public String getCommandName() {
		return myCommandName;
	}

	@Override
	public ArrayList<CalendarEvent> filter(ArrayList<Object> parameters,
			ArrayList<CalendarEvent> events) {

		InOrderFilter myFilter = new InOrderFilter();
		ArrayList<CalendarEvent> eventsToReturn = events;
		Collections.sort(eventsToReturn, myFilter);

		return eventsToReturn;
	}

	private class InOrderFilter implements Comparator<CalendarEvent> {

		public int compare(CalendarEvent o1, CalendarEvent o2) {

			DateTime start1 = o1.getMyEndTime();
			DateTime start2 = o2.getMyEndTime();

			int test = start1.compareTo(start2);
			return test;

		}

	}

}
