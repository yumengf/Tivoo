import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.H2;
import com.hp.gagawa.java.elements.H5;
import com.hp.gagawa.java.elements.Html;
import com.hp.gagawa.java.elements.Text;

public class Output {

	private List<CalendarEvent> myCalendar;
	private Map<Integer, ArrayList<CalendarEvent>> myList;
	
	/*
	 * Constructor1
	 */
	public Output(ArrayList<CalendarEvent> cal) {	
		myCalendar = cal;
		myList = new HashMap<Integer, ArrayList<CalendarEvent>>();
	}
	
	/*
	 * Constructor2
	 */
	public Output() {		
		myCalendar = new ArrayList<CalendarEvent>();
		myList = new HashMap<Integer, ArrayList<CalendarEvent>>();
	}
	
	/*
	 * Sort CalendarEvent into Each day in the week, stored in Map
	 */
	private void constructMap() {
		for(int i = 0; i <7; i++) {
			myList.put(i, new ArrayList<CalendarEvent>());
		}	
		
		for(int i = 0; i< myCalendar.size(); i++) {
			CalendarEvent cal = myCalendar.get(i);
			
			if((cal.startDayOfWeek()== 0)&&!myList.get(0).contains(cal)){
				ArrayList<CalendarEvent> currentList = myList.get(0);
				currentList.add(cal);
				myList.put(0, currentList);
			}
			else if((cal.startDayOfWeek() == 1)&&!myList.get(1).contains(cal)){
				ArrayList<CalendarEvent> currentList = myList.get(1);
				currentList.add(cal);
				myList.put(1, currentList);
			}
			else if((cal.startDayOfWeek() == 2)&&!myList.get(2).contains(cal)){
				ArrayList<CalendarEvent> currentList = myList.get(2);
				currentList.add(cal);
				myList.put(2, currentList);
			}
			else if((cal.startDayOfWeek() == 3)&&!myList.get(3).contains(cal)){
				ArrayList<CalendarEvent> currentList = myList.get(3);
				currentList.add(cal);
				myList.put(3, currentList);
			}
			else if((cal.startDayOfWeek() == 4)&&!myList.get(4).contains(cal)){
				ArrayList<CalendarEvent> currentList = myList.get(4);
				currentList.add(cal);
				myList.put(4, currentList);
			}
			else if((cal.startDayOfWeek() == 5)&&!myList.get(5).contains(cal)){
				ArrayList<CalendarEvent> currentList = myList.get(5);
				currentList.add(cal);
				myList.put(5, currentList);
			}
			else if((cal.startDayOfWeek() == 6)&&!myList.get(6).contains(cal)){
				ArrayList<CalendarEvent> currentList = myList.get(6);
				currentList.add(cal);
				myList.put(6, currentList);
			}	
		}
	}
	
	/*
	 * Main process, given myList, design the html output format
	 */
	public void outputFile() {		
		constructMap();
					
		Html html = new Html();
		Body body = new Body();
		body.setBgcolor("grey");
		
		for(int i =0; i <7; i++) {
			Div div = new Div();
			H2 h = new H2();
			h.appendText(Transfer(i)).setAlign("center");
			div.appendChild(h);
			
			if(myList.containsKey(i)&&!myList.get(i).isEmpty()) {
				for(int j = 0; j< myList.get(i).size(); j++) {
					CalendarEvent c = myList.get(i).get(j);
					
					Div div2 = new Div();
					String myAddress = EventFile(i,c);
					
					A link = new A();
					link.setHref(myAddress).setTarget("_blank");
					H5 head = new H5();
					head.appendText(c.getMyName());
					head.setAlign("center");
					link.appendChild(head);
					div2.appendChild(link);	
					div.appendChild(div2);
				}
			}
			body.appendChild(div);
		}
		html.appendChild(body);
		writeInFile(html, "Output/outputEvent.htm");
	}
		
	private String EventFile(int i, CalendarEvent cal) {
		Html html = new Html();
		Body body = new Body();
		body.setBgcolor("grey");
		
		H1 head = new H1();
		head.setAlign("center");
		head.appendChild(new Text("Detail Information about the Event:"));
		body.appendChild(head);
		
		Div div = new Div();
		
		cal.appendInformation(div);
		
//		div.appendChild(new Text("Event Name: " + cal.getMyName() + "<br />"));
//		div.appendChild(new Text("Event Location: " + cal.getMyLocation() + "<br />"));
//		div.appendChild(new Text("Event Start Time: " + cal.getMyStartDate().toString("EEEE dd MMMM, yyyy HH:mm:ssa") + "<br />"));
//		div.appendChild(new Text("Event End Time: " + cal.getMyEndDate().toString("EEEE dd MMMM, yyyy HH:mm:ssa") + "<br />"));
//		
		A link = new A();
		
		String l = cal.getMyLink();
		
		int pos = l.indexOf(".com") + 4;
		l = l.substring(0, pos);
		
		link.setHref("http://" + l).setTarget("_blank");
		link.appendChild(new Text("Click Here for LINK"));
		
		div.appendChild(link);
		body.appendChild(div);
		html.appendChild(body);
				
		String preAddress =  i + cal.getMyName().substring(0,4) + ".htm";
		String address = "Output/" + preAddress;
		writeInFile(html, address);		
		return preAddress;
	}
		
	/*
	 * write HTML into specified file address
	 */
	private void writeInFile(Html html, String address) {				
		try {
			File file = new File(address);		
	        PrintWriter out = new PrintWriter(new FileOutputStream(file));
	        out.println(html.write());								//write into file.
	        //System.out.println(output.write());				//test code line
	        out.close();
			} catch (FileNotFoundException e) {
                e.printStackTrace();
        }
	}
	
	private String Transfer(int i) {
		switch (i) {
		case 0: return "Sunday";
		case 1: return "Monday";
		case 2: return "Tuesday";
		case 3: return "Wednesday";
		case 4: return "Thursday";
		case 5: return "Friday";
		case 6: return "Saturday";
		}
		return null;
	}
}
