package process;

import input.CalendarEvent;
import input.TVshow; 

import java.util.ArrayList;



public class ClassSpecificFilter implements Filter{
	private String myCommandName = "ClassSpecific"; 
	
    public String getCommandName() {
    	return myCommandName; 
    }

    public ArrayList<CalendarEvent> filter(ArrayList<Object> parameters, ArrayList<CalendarEvent> myEvents) {
    	  ArrayList<CalendarEvent> myEventsToReturn = new ArrayList<CalendarEvent>();   	  
    	  ArrayList<String> myActors = new ArrayList<String>(); 
    	  for (Object p: parameters){
    		  myActors.add((String) p);
    	  }
    	  
    	  // loops through events to be filter
    	  for (CalendarEvent currentEvent: myEvents){
              for (String k: myActors){
    		  // uses the hasKeyword method in the CalendarEvent class to see if the title contains the word
            	  TVshow currentShow = (TVshow)currentEvent; 
            	  if (currentShow.hasActor(k))
            		  myEventsToReturn.add(currentEvent); 
            	 
              } 
          }
          return myEventsToReturn;
    }
}
