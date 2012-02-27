package output;

import java.util.ArrayList;
import java.util.List;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.H5;
import com.hp.gagawa.java.elements.Html;
import com.hp.gagawa.java.elements.Li;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Ul;
import input.CalendarEvent;

public class SortListOutput extends Output {
	private List<CalendarEvent> myCalendar;
	
	public SortListOutput(ArrayList<CalendarEvent> cal) {
		myCalendar = cal;
	}	
	
	@Override
	public void outputFile(String string) {
		Html html = new Html();
		Body body = new Body();
		html.appendChild(body);
		
		body = constructDayFrame(body, string);
		body = addEventList(body);			//Add Event list to each day
		writeInFile(html, "Output/SortOutput.htm");
	}

	/*
	 * Takes in a Body object, initialize the title by kind of sort
	 * Used in outputFile method.
	 */	
	private Body constructDayFrame(Body body, String string) {
		H1 title = new H1().setAlign("center");
		body.appendChild(title);
		title.appendChild(new Text(string));
		return body;
	}
	
	/*
	 * Takes in a Body object, append Events within a day, return the body. 
	 * Used in outputFile method.
	 */
	private Body addEventList(Body body) {
		Ul ul = new Ul();	
		body.appendChild(ul);
		
		
		for(int i = 0; i < myCalendar.size(); i++) {
			Li li = new Li();
			ul.appendChild(li);	
			CalendarEvent c = myCalendar.get(i);
			A link = new A().setHref(EventFile("Sort", i, 1, c)).setTarget("_blank");
			H5 head = new H5().appendText(c.getMyName()).setAlign("center");				
			link.appendChild(head);
			li.appendChild(link);
		}
		return body;
	}
	
}