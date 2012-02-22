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
import com.hp.gagawa.java.elements.Li;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Tr;
import com.hp.gagawa.java.elements.Ul;

public class Output {
	private List<CalendarEvent> myCalendar;
	private Map<Integer, ArrayList<CalendarEvent>> myList;
	private static final String[] Weekday = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	
	public Output(ArrayList<CalendarEvent> cal) {			//Constructor
		myCalendar = cal;
		myList = new HashMap<Integer, ArrayList<CalendarEvent>>();
		initialMap();
	}
	
	public void outputFile() {		//Main process. Using parameter myList, process the html output
		constructMap();			
		Html html = new Html();
		Body body = new Body().setBgcolor("black");
		html.appendChild(body);
		Table table = new Table().setBgcolor("grey").setBorder("2");
		Tr tr = new Tr().setAlign("center");
		table.appendChild(tr);
		body.appendChild(table);
		
		for(int i =0; i <7; i++) {
			Td td = new Td().setAlign("center");
			td.appendChild(new H2().appendText(Weekday[i]).setAlign("center"));
			tr.appendChild(td);
		}
		
		Tr tr2 = new Tr().setAlign("center");
		table.appendChild(tr2);
		for(int i =0; i <7; i++) {
			Td td2 = new Td().setAlign("top");
			if(myList.containsKey(i)&&!myList.get(i).isEmpty()) {
				for(int j = 0; j< myList.get(i).size(); j++) {
					CalendarEvent c = myList.get(i).get(j);
					Ul ul = new Ul();	
					Li li = new Li();
					A link = new A().setHref(EventFile(i,j, c)).setTarget("_blank");
					H5 head = new H5().appendText(c.getMyName()).setAlign("center");				
					link.appendChild(head);
					li.appendChild(link);
					ul.appendChild(li);	
					td2.appendChild(ul);
				}
			}
			tr2.appendChild(td2);
		}
		writeInFile(html, "Output/outputEvent.htm");
	}
		
	private String EventFile(int i, int j, CalendarEvent cal) {		//Create comments in each detail event page, return address of the page
		Html html = new Html();
		Body body = new Body().setBgcolor("grey");	
		H1 head = new H1().appendChild(new Text("Detail Information about the Event:")).setAlign("center");
		body.appendChild(head);
		Div div = new Div();
		cal.appendInformation(div);
		A link = new A().setHref("http://" + cal.getMyLink()).setTarget("_blank").appendChild(new Text("Link for Detail Page"));			//attach link page
		
		div.appendChild(link);
		body.appendChild(div);
		html.appendChild(body);
				
		String preAddress =  i + j + ".htm";	//construct filename for specific event
		writeInFile(html, "Output/" + preAddress);		
		return preAddress;
	}
	
	private void writeInFile(Html html, String address) {		//write HTML into specified file address(final step)
		try {
			File file = new File(address);		
	        PrintWriter out = new PrintWriter(new FileOutputStream(file));
	        out.println(html.write());								//write into file.
	        out.close();
			} catch (FileNotFoundException e) {
                e.printStackTrace();
        }
	}
	
	private void constructMap() {		//Sort CalendarEvent into Each day in the week, stored in Map	
		for(CalendarEvent cal : myCalendar) {			//Sort date into each day
			for(int j = 0; j <7; j++) {
				if((cal.startDayOfWeek() == j)&&!myList.get(j).contains(cal)){
					ArrayList<CalendarEvent> currentList = myList.get(j);
					currentList.add(cal);
					myList.put(j, currentList);
				}
			}
		}
	}
	
	private void initialMap() {		//Initialize map 
		for(int i = 0; i <7; i++) {
			myList.put(i, new ArrayList<CalendarEvent>());
		}
	}
}
