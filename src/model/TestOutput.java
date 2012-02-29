package model;

import input.CalendarEvent;

import java.util.ArrayList;
import org.joda.time.DateTime;

import output.Output;
import output.WeekOutput;



public class TestOutput {

	public static void main(String[] args) {
		
		ArrayList<CalendarEvent> list = new ArrayList<CalendarEvent>();
		
		list.add(new CalendarEvent("BAIDU", "SHANGHAI", new DateTime(2000,1,5,0,0), new DateTime(2000,1,5,2,0), "www.baidu.com"));
		list.add(new CalendarEvent("GOOGLE", "DURHAM", new DateTime(2000,1,7,3,0), new DateTime(2000,1,7,4,0), "www.goolgle.com"));
		list.add(new CalendarEvent("BAIDU", "SHANGHAI", new DateTime(2000,1,11,0,0), new DateTime(2000,1,11,2,0), "www.baidu.com"));
		list.add(new CalendarEvent("BAIDU", "SHANGHAI", new DateTime(2000,1,22,0,0), new DateTime(2000,1,22,2,0), "www.baidu.com"));
		list.add(new CalendarEvent("BAIDU", "SHANGHAI", new DateTime(2000,1,30,0,0), new DateTime(2000,1,30,2,0), "www.baidu.com"));
		
		Output output = new WeekOutput(list);
		output.outputFile("DateSort");
		
		/*DateTime test = new DateTime(2011,12,4,0,0);
		
		System.out.println(test.getDayOfWeek());*/
	}
	
	
}
