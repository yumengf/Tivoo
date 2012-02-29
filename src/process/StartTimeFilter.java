package process;

import input.CalendarEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.joda.time.DateTime;


public  class StartTimeFilter implements Filter{
	
	 
	private String myCommandName = "start in order"; 

	@Override
    public String getCommandName() {
	    // TODO Auto-generated method stub
	    return myCommandName;
    }
	
	public ArrayList<CalendarEvent> filter(ArrayList<Object> parameters,
            ArrayList<CalendarEvent> events) {
	    InOrderFilter myFilter = new InOrderFilter(); 
	    ArrayList<CalendarEvent> eventsToReturn = events; 
	    Collections.sort(eventsToReturn, myFilter);
	    
	    return eventsToReturn; 
    }

	
    private class InOrderFilter implements Comparator<CalendarEvent>{

        public int compare(CalendarEvent o1, CalendarEvent o2) {
			
			DateTime start1 = o1.getMyStartTime(); 
			DateTime start2 = o2.getMyStartTime(); 
			
			int test = start1.compareTo(start2); 
			return test; 
			
        }
    	
    }
 
}


