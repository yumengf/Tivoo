package output;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import Tivoo.CalendarEvent;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.Font;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.H4;
import com.hp.gagawa.java.elements.H5;
import com.hp.gagawa.java.elements.Html;
import com.hp.gagawa.java.elements.Li;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Tr;
import com.hp.gagawa.java.elements.Ul;

/*
 *MonthOutput class receive list of CalendarEvent objects with information of eventName, date, location, 
 *startTime and endTime, and then output the information into a html file with specific information linked to each event.
 */
public class MonthOutput extends Output {
	private List<CalendarEvent> myCalendar;
	private Map<Integer, ArrayList<CalendarEvent>> myEventMap;
	private static final String[] Month = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	public MonthOutput(ArrayList<CalendarEvent> cal) {
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
		if(!myCalendar.isEmpty()) {		
		Body body = new Body();
		html.appendChild(body);
		body = constructMonthFrame(body);
		Table table = new Table().setBgcolor("grey").setBorder("2");
		body.appendChild(table);
		
		table = addEventList(table);			//Add Event list to each day
		}
		writeInFile(html, "Output/MonthOutput.htm");
	}

	/*
	 * Takes in a Body object, initialize the title of day in the week
	 * Used in outputFile method.
	 */	
	private Body constructMonthFrame(Body body) {
		H1 title = new H1().setAlign("center");
		body.appendChild(title);
		int i = myCalendar.get(0).getMyStartTime().getMonthOfYear();
		title.appendText(Month[i-1]).setAlign("center");	
		return body;
	}
	
	
	/*
	 * Takes in a Table object, append Events with corresponding day, return the table. 
	 * Used in outputFile method.
	 */
	private Table addEventList(Table table) {
		Tr tr = new Tr().setAlign("center");
		table.appendChild(tr);
		
		for(int i =1; i <=31; i++) {	
			Td td = new Td().setAlign("top");
			H4 title = new H4().setAlign("center");
			Font font = new Font().setColor("red");
			title.appendChild(new Text(i)).appendChild(font);
			td.appendChild(title);
			
			if(!myEventMap.get(i).isEmpty()) {
				for(int j = 0; j< myEventMap.get(i).size(); j++) {
					CalendarEvent c = myEventMap.get(i).get(j);
					Ul ul = new Ul();	
					Li li = new Li();
					A link = new A().setHref(eventFile("Month", i,j, c)).setTarget("_blank");
					H5 head = new H5().appendText(c.getMyName()).setAlign("center");				
					link.appendChild(head);
					li.appendChild(link);
					ul.appendChild(li);
					td.appendChild(ul);
				}
			}
			tr.appendChild(td);
		}
		return table;
	}
	
	/*
	 * Sort CalendarEvent into Map according to each day in a month. 
	 * Used in Constructor
	 */
	private void constructMap() {
		for(CalendarEvent cal : myCalendar) {			//Sort date into each day
			for(int j = 1; j <=31; j++) {
				if((cal.getMyStartTime().getDayOfMonth() == j)&&!myEventMap.get(j).contains(cal)){
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
		for(int i = 1; i <= 31.; i++) {
			myEventMap.put(i, new ArrayList<CalendarEvent>());
		}
	}
}
