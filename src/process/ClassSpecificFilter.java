package process;

import input.CalendarEvent;
import input.TVshow;

import java.util.ArrayList;

/*
 * The Specific Filter is created to just filter actors as keywords and 
 * makes sure to not filter out other events just because they are not 
 * TV Shows
 */
public class ClassSpecificFilter implements Filter {

	private String myCommandName = "ClassSpecific";

	public String getCommandName() {
		return myCommandName;
	}

	// this filter actors similarly to the keyword filter just looking for
	// actors
	public ArrayList<CalendarEvent> filter(ArrayList<Object> parameters,
			ArrayList<CalendarEvent> myEvents) {
		ArrayList<CalendarEvent> myEventsToReturn = new ArrayList<CalendarEvent>();
		ArrayList<String> myActors = new ArrayList<String>();

		// checks to make sure these parameters are in fact Strings
		for (Object p : parameters) {
			if (p instanceof String) {
				// find the start date and the end date of the time frame
				myActors.add((String) p);
			} else {
				System.out.println("Instance " + p.toString()
						+ " was not a String Type!");
			}
		}

		// loops through events to be filter
		for (CalendarEvent currentEvent : myEvents) {
			// checks all the actors
			for (String k : myActors) {
				// makes sure to only filter if the event is a TVshow
				TVshow currentShow = (TVshow) currentEvent;
				try {
					if (currentShow.hasActor(k))
						myEventsToReturn.add(currentEvent);
				} catch (ClassCastException cce) {
					myEventsToReturn.add(currentEvent);
				}

			}
		}
		return myEventsToReturn;
	}
}
