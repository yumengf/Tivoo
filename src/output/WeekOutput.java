package output;

import input.CalendarEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.H2;
import com.hp.gagawa.java.elements.H5;
import com.hp.gagawa.java.elements.Html;
import com.hp.gagawa.java.elements.Li;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Tr;
import com.hp.gagawa.java.elements.Ul;

/*
 *WeekOutput class receive list of CalendarEvent objects with information of eventName, date, location, 
 *startTime and endTime, and then output the information into a html file with specific information linked to each event.
 */
public class WeekOutput  extends Output{
	private List<CalendarEvent> myCalendar;
	private Map<Integer, ArrayList<CalendarEvent>> myEventMap;
	private static final String[] Weekday = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	
	public WeekOutput (ArrayList<CalendarEvent> cal) {
		myCalendar = cal;
		myEventMap = new HashMap<Integer, ArrayList<CalendarEvent>>();
		initialMap();
		constructMap();		
	}
	
	/*
	 * Main output process. Take in myEventMap, and output into html file.
	 */
	@Override
	public void outputFile(String string) {	
		Html html = new Html();
		Body body = new Body();
		html.appendChild(body);
		Table table = new Table().setBgcolor("grey").setBorder("2");
		body.appendChild(table);
		
		table = constructWeekFrame(table);	//Initialize table within a week
		table = addEventList(table);			//Add Event list to each day
		
		writeInFile(html, "Output/WeekOutput.htm");
	}
	
	/*
	 * Takes in a Table object, initialize the table by adding  each day in a week.
	 * Used in outputFile method.
	 */	
	private Table constructWeekFrame(Table table) {
		Tr tr = new Tr().setAlign("center");
		table.appendChild(tr);		
		//Create table name with days in a week
		for(int i =0; i <7; i++) {
			Td td = new Td().setAlign("center");
			td.appendChild(new H2().appendText(Weekday[i]).setAlign("center"));
			tr.appendChild(td);
		}
		return table;
	}
	/*
	 * Takes in a Table object, append Events with corresponding day, return the table. 
	 * Used in outputFile method.
	 */
	private Table addEventList(Table table) {
		Tr tr = new Tr().setAlign("center");
		table.appendChild(tr);
		
		for(int i =0; i <7; i++) {
			Td td = new Td().setAlign("center");
			if(myEventMap.containsKey(i)&&!myEventMap.get(i).isEmpty()) {
				for(int j = 0; j< myEventMap.get(i).size(); j++) {
					CalendarEvent c = myEventMap.get(i).get(j);
					Ul ul = new Ul();	
					Li li = new Li();
					A link = new A().setHref(EventFile("Week", i,j, c)).setTarget("_blank");
					H5 head = new H5().appendText(c.getMyName()).setAlign("center");				
					link.appendChild(head);
					li.appendChild(link);
					ul.appendChild(li);	
					td.appendChild(ul);
				}
			}
			else td.appendChild(new Text("Empty!"));
			tr.appendChild(td);
		}
		return table;
	}
	
	/*
	 * Sort CalendarEvent into Map according to weekday name. 
	 * Used in Constructor
	 */
	private void constructMap() {
		for(CalendarEvent cal : myCalendar) {			//Sort date into each day
			for(int j = 0; j <7; j++) {
				if((cal.startDayOfWeek() == j)&&!myEventMap.get(j).contains(cal)){
					ArrayList<CalendarEvent> currentList = myEventMap.get(j);
					currentList.add(cal);
					myEventMap.put(j, currentList);
				}
			}
		}
	}
	
	/*
	 * Initialize map. 
	 * Used in Constructor
	 */
	private void initialMap() {
		for(int i = 0; i <7; i++) {
			myEventMap.put(i, new ArrayList<CalendarEvent>());
		}
	}
}
