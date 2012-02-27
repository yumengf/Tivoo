package process;


import input.CalendarEvent;
import java.util.ArrayList;

public interface Filter {
	public abstract String getCommandName(); 
	public abstract ArrayList<CalendarEvent> filter(ArrayList<Object> parameters, ArrayList<CalendarEvent> events); 
	
}