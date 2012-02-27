package process;

import input.CalendarEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public  class NameReverseFilter implements Filter{
	
	 
	private String myCommandName = "name reverse order"; 

	@Override
    public String getCommandName() {
	    // TODO Auto-generated method stub
	    return myCommandName;
    }
	
	@Override
    public ArrayList<CalendarEvent> filter(ArrayList<Object> parameters,
            ArrayList<CalendarEvent> events) {
	    ReverseOrderFilter myFilter = new ReverseOrderFilter(); 
	    ArrayList<CalendarEvent> eventsToReturn = events; 
	    Collections.sort(eventsToReturn, myFilter);
	    
	    return eventsToReturn; 
    }

	
    private class ReverseOrderFilter implements Comparator<CalendarEvent>{

        public int compare(CalendarEvent o1, CalendarEvent o2) {
			
        	int test = o1.getMyName().compareTo(o2.getMyName()); 
			if (test > 0)
				return -1; 
			else if (test < 0)
				return 1; 
			else 
				return 0; 
        }
    	
    }
 
}