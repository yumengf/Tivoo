package process;

import input.CalendarEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public  class NameOrderFilter implements Filter{
	
	 
	private String myCommandName = "nameOrder"; 

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
			
			return o1.getMyName().compareTo(o2.getMyName()); 
			
        }
    	
    }
 
}
