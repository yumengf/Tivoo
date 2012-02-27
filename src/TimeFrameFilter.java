import java.util.ArrayList;
import org.joda.time.DateTime;

public class TimeFrameFilter implements Filter{
	private String myCommandName = "TimeFrame"; 

    public String getCommandName() {  
	    return myCommandName; 
    }

	
    public ArrayList<CalendarEvent> filter(ArrayList<Object> parameters, ArrayList<CalendarEvent> myEvents) {
    	 ArrayList<CalendarEvent> myEventsCopy = new ArrayList<CalendarEvent>();
    	 DateTime startDate = (DateTime)parameters.get(0); 
 		 DateTime endDate = (DateTime)parameters.get(1);
    	
    	 for (CalendarEvent currentEvent: myEvents){
             //Start Date is greater than or equal to start date
             if (currentEvent.isInTimeFrame(startDate, endDate))
                          myEventsCopy.add(currentEvent);
         }
         return myEventsCopy;
    }
}