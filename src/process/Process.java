package process;

import input.CalendarEvent;

import java.util.ArrayList;
import java.util.List;

public class Process {
	// creates a new filter class
	FilterFactory myFilterFactory = new FilterFactory();

	public Process() throws ClassNotFoundException {
		try {
			myFilterFactory = new FilterFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<CalendarEvent> filter(String myCommand,
			ArrayList<Object> myParameters,
			ArrayList<CalendarEvent> eventsToFilter) throws Exception {
		// uses the filter factory to get the correct event filter using the
		// command line
		Filter myFilter = myFilterFactory.getFilter(myCommand);
		// calls the filter method in the specified class and returns an
		// arraylist of the events after being filtered
		return myFilter.filter(myParameters, eventsToFilter);
	}

	public List<CalendarEvent> filter(String filters, List<?> l,
			List<CalendarEvent> list) throws Exception {
		// TODO Auto-generated method stub
		return filter(filters, (ArrayList<Object>)l, (ArrayList<CalendarEvent>)list);
	}
}
