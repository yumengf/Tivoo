import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Process {
	// creates a new filter class
	FilterFactory myFilterFactory = new FilterFactory();
	
	public Process() throws ClassNotFoundException{
		try {
	        myFilterFactory = new FilterFactory();
        } catch (Exception e) {
	        e.printStackTrace();
        } 
	}
	
	public ArrayList<CalendarEvent> filter (String myCommand, ArrayList<Object> myParameters, ArrayList<CalendarEvent> eventsToFilter) throws SecurityException, IllegalArgumentException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException{		
		// uses the filter factory to get the correct event filter using the command line 
		Filter myFilter = myFilterFactory.getFilter(myCommand);		
		// calls the filter method in the specified class and returns an arraylist of the events after being filtered
		return myFilter.filter(myParameters, eventsToFilter); 	
	}
}

