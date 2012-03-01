package output;

import input.CalendarEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * Output Factory store subclass of Output into a map.
 * Use as factory method
 */
public class OutputFactory {
	private ArrayList<CalendarEvent> myCalendar;
	private Map<String, Output> myMap;

	public OutputFactory(ArrayList<CalendarEvent> cal) {
		myCalendar = cal;
		constructMap();
	}

	private void constructMap() {
		myMap = new HashMap<String, Output>();
		myMap.put("day", new DayOutput(myCalendar));
		myMap.put("week", new WeekOutput(myCalendar));
		myMap.put("month", new MonthOutput(myCalendar));
		myMap.put("sort", new SortListOutput(myCalendar));
	}

	public void output(String typeName, String input) {
		Output myOutput = myMap.get(typeName);
		myOutput.outputFile(input);
	}

}
