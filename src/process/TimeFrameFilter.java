package process;

import input.CalendarEvent;

import java.util.ArrayList;
import org.joda.time.DateTime;

/*
 * Returns an arraylist of events within the give time frame 
 */
public class TimeFrameFilter implements Filter {
	private String myCommandName = "timeFrame";

	public String getCommandName() {
		return myCommandName;
	}

	public ArrayList<CalendarEvent> filter(ArrayList<Object> parameters,
			ArrayList<CalendarEvent> myEvents) {
		ArrayList<CalendarEvent> myEventsCopy = new ArrayList<CalendarEvent>();

		DateTime startDate = new DateTime();
		DateTime endDate = new DateTime();

		for (Object p : parameters) {
			if (p instanceof DateTime) {
				// find the start date and the end date of the time frame
				startDate = (DateTime) parameters.get(0);
				endDate = (DateTime) parameters.get(1);
			} else {
				System.out.println("Instance " + p.toString()
						+ " was not a DateTime Type!");
			}
		}

		for (CalendarEvent currentEvent : myEvents) {
			// uses time frame method to see if it falls between the dates
			if (currentEvent.isInTimeFrame(startDate, endDate))
				myEventsCopy.add(currentEvent);
		}
		return myEventsCopy;
	}
}