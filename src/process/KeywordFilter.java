package process;
import input.*;
import java.util.ArrayList;

public class KeywordFilter implements Filter{
	private String myCommandName = "keyword"; 
	// private String myKeyword; 
	public KeywordFilter() {}

    public String getCommandName() {
    	return myCommandName; 
    }

    public ArrayList<CalendarEvent> filter(ArrayList<Object> parameters, ArrayList<CalendarEvent> myEvents) {
    	  ArrayList<CalendarEvent> myEventsToReturn = new ArrayList<CalendarEvent>();
    	  String myKeyword = (String)parameters.get(0);
    	  // loops through events to be filter
    	  for (CalendarEvent currentEvent: myEvents){
              // uses the hasKeyword method in the CalendarEvent class to see if the title contains the word
              if (currentEvent.hasKeyWord(myKeyword))
            	  myEventsToReturn.add(currentEvent); 
          }
          return myEventsToReturn;
    }
}