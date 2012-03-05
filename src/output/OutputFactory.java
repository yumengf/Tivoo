package output;

import input.CalendarEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Output Factory store subclass of Output into a map.
 * Use as factory method
 */
public class OutputFactory {
	private ArrayList<CalendarEvent> myCalendar;
	private Map<String, Output> myMap;

	public OutputFactory(List<CalendarEvent> list) {
		myCalendar = (ArrayList<CalendarEvent>) list;
		constructMap();
	}

	private void constructMap() {
		myMap = new HashMap<String, Output>();
		myMap.put("Day", new DayOutput(myCalendar));
		myMap.put("Week", new WeekOutput(myCalendar));
		myMap.put("Month", new MonthOutput(myCalendar));
		myMap.put("Sort", new SortListOutput(myCalendar));
	}

	public void output(String typeName, String input) {
		Output myOutput = myMap.get(typeName);
		myOutput.outputFile(input);
	}

}
