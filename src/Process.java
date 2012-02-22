import java.util.ArrayList;
import org.joda.time.DateTime;

public class Process {

       private ArrayList<CalendarEvent> myEvents = new ArrayList<CalendarEvent>();
       public Process (ArrayList<CalendarEvent> inputEvents){
               myEvents = inputEvents;
       }

       /*
        * Returns an Arraylist of events which contains all the events
        * that contain a "keyword"
        */
       public ArrayList<CalendarEvent> keyword(String keyword){
               ArrayList<CalendarEvent> myEventsToReturn = new ArrayList<CalendarEvent>();
               for (CalendarEvent currentEvent: myEvents){
                       // if the event has an index != -1. it means it contains the word
                       // and therefore adds it to the list
                       if (currentEvent.hasKeyWord(keyword)){
                               myEventsToReturn.add(currentEvent);
                       }
               }
               return myEventsToReturn;
       }

       /*
        * Returns an Arraylist of events which contains all the events
        * within a given time frame
        */
       public ArrayList<CalendarEvent> timeFrame(DateTime start, DateTime finish){
               ArrayList<CalendarEvent> myEventsCopy = new ArrayList<CalendarEvent>();
               for (CalendarEvent currentEvent: myEvents){
                       //Start Date is greater than or equal to start date
                       if(currentEvent.isInTimeFrame(start, finish)){
                    	   myEventsCopy.add(currentEvent);
                       }
                       
               }
               return myEventsCopy;
       }


}