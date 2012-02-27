package output;

import java.util.ArrayList;
import java.util.List;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.H5;
import com.hp.gagawa.java.elements.Html;
import com.hp.gagawa.java.elements.Li;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Tr;
import com.hp.gagawa.java.elements.Ul;

import input.CalendarEvent;

/*
 *DayOutput class receive list of CalendarEvent objects with information of eventName, date, location, 
 *startTime and endTime, and then output the information into a html file with specific information linked to each event.
 */
public class DayOutput extends Output {
	private List<CalendarEvent> myCalendar;
	private static final String[] Weekday = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	
	public DayOutput(ArrayList<CalendarEvent> cal) {
		myCalendar = cal;
	}
	
	public void outputFile(String string) {
		Html html = new Html();
		Body body = new Body();
		html.appendChild(body);
		body = constructDayFrame(body);
		Table table = new Table().setBgcolor("grey").setBorder("2").setAlign("center");
		body.appendChild(table);
		
		table = addEventList(table);			//Add Event list to each day
		
		writeInFile(html, "Output/DayOutput.htm");
	}
	
	/*
	 * Takes in a Body object, initialize the title of day in the week
	 * Used in outputFile method.
	 */	
	private Body constructDayFrame(Body body) {
		H1 title = new H1().setAlign("center");
		body.appendChild(title);
		int i = myCalendar.get(0).startDayOfWeek();
		title.appendText(Weekday[i]).setAlign("center");	
		return body;
	}
	/*
	 * Takes in a Table object, append Events within a day, return the table. 
	 * Used in outputFile method.
	 */
	private Table addEventList(Table table) {
		Tr tr = new Tr().setAlign("center");
		table.appendChild(tr);
		Td td = new Td().setAlign("center");
		tr.appendChild(td);
		Ul ul = new Ul();	
		td.appendChild(ul);
		Li li = new Li();
		ul.appendChild(li);	
		
		for(int i = 0; i < myCalendar.size(); i++) {
			CalendarEvent c = myCalendar.get(i);
			A link = new A().setHref(EventFile("Day", i, 1, c)).setTarget("_blank");
			H5 head = new H5().appendText(c.getMyName()).setAlign("center");				
			link.appendChild(head);
			li.appendChild(link);
		}
		return table;
	}
}
