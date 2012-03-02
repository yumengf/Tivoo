package process;

import input.CalendarEvent;

import java.util.ArrayList;

/*
 * When given an arraylist of keywords, this class returns an arraylist 
 * of events that only contain these key words
 */
public class KeywordFilter implements Filter {

	private String myCommandName = "keepKeyword";

	public String getCommandName() {
		return myCommandName;
	}

	// override method from Filter
	public ArrayList<CalendarEvent> filter(ArrayList<Object> parameters,
			ArrayList<CalendarEvent> myEvents) throws ClassCastException {

		ArrayList<CalendarEvent> myEventsWith = new ArrayList<CalendarEvent>();
		ArrayList<String> myKeywords = new ArrayList<String>();

		// checks to make sure these parameters are in fact Strings
		for (Object p : parameters) {
			if (p instanceof String) {
				// find the start date and the end date of the time frame
				myKeywords.add((String) p);
			} else {
				System.out.println("Instance " + p.toString()
						+ " was not a String Type!");
			}
		}

		// loops through events to be filtered
		for (CalendarEvent currentEvent : myEvents) {
			// loops through the keywords
			for (String k : myKeywords) {
				// uses the hasKeyword method in the CalendarEvent class to see
				// if the title contains the current keyword
				if (currentEvent.hasKeyWord(k))
					myEventsWith.add(currentEvent);

			}
		}
		return myEventsWith;
	}
}