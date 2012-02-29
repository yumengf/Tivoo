package process;


import input.CalendarEvent;

import java.util.ArrayList;

public class RemoveKeywordFilter implements Filter{
	
	private String myCommandName = "removeKeyword"; 
	

    public String getCommandName() {
    	return myCommandName; 
    }

    public ArrayList<CalendarEvent> filter(ArrayList<Object> parameters, ArrayList<CalendarEvent> myEvents) {
    	  ArrayList<CalendarEvent> myEventsToReturn = new ArrayList<CalendarEvent>();
    	  ArrayList<String> myKeywords = new ArrayList<String>(); 
    	  for (Object p: parameters){
    		  myKeywords.add((String) p);
    	  }
    	  
    	  // loops through events to be filter
    	  for (CalendarEvent currentEvent: myEvents){
              for (String k: myKeywords){
    		  // uses the hasKeyword method in the CalendarEvent class to see if the title contains the word
            	  if (!currentEvent.hasKeyWord(k))
            		  myEventsToReturn.add(currentEvent); 
         
              } 
          }
          return myEventsToReturn;
    }
}
