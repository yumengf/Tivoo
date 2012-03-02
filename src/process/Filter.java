package process;

import input.CalendarEvent;
import java.util.ArrayList;

public interface Filter {
	public String getCommandName();

	public ArrayList<CalendarEvent> filter(
			ArrayList<Object> parameters, ArrayList<CalendarEvent> events);

}