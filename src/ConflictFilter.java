package Process;

import java.util.ArrayList;

import org.joda.time.DateTime;

import Tivoo.CalendarEvent;

public class ConflictFilter implements Filter {
	private String myCommandName = "conflict"; 
	// private String myKeyword; 
	
	public ConflictFilter() {};
	
	@Override
	public String getCommandName() {
		return myCommandName;
	}

	@Override
	public ArrayList<CalendarEvent> filter(ArrayList<Object> parameters, ArrayList<CalendarEvent> myEvents) {
	   	 ArrayList<CalendarEvent> myEventsCopy = new ArrayList<CalendarEvent>();
	   	
	   	 for (CalendarEvent currentEvent: myEvents){
	            DateTime startDate = currentEvent.getMyStartTime();
	            DateTime endDate = currentEvent.getMyEndTime();
	            
	            for(CalendarEvent compareEvent : myEvents) {
	            	if(!compareEvent.equals(currentEvent)) {
	            		if (compareEvent.isInTimeFrame(startDate, endDate))
	                        if(!myEventsCopy.contains(compareEvent)) myEventsCopy.add(compareEvent);
	            		if(!myEventsCopy.contains(currentEvent)) myEventsCopy.add(currentEvent);
	            	}
	            }
	        }
	        return myEventsCopy;
	}

    
}
