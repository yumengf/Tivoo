package input;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/*
 * NFLParser class extends the abstract class Parser.java
 * It parses NFLcalendar.xml and return and arraylist of Calendar event
 * with relevant information: start date, end date, link, location, and summary
 */
public class NFLParser implements Parser{
	private DateTimeFormatter myFormatter = DateTimeFormat.forPattern("yyyy-MM-dd H:mm:ss");

	public NFLParser() {
	}
	/*
	 * parse() returns an ArrayList of CalendarEvent.java which contains
	 * the information of start date, end date, link, location, and summary
	 * (non-Javadoc)
	 * @see Parser#parse()
	 */
	public ArrayList<CalendarEvent> parse(Element rootNode) throws JDOMException, IOException{
		ArrayList<CalendarEvent> collection = new ArrayList<CalendarEvent>();
		List<?> list = rootNode.getChildren("row");

		for(Object e : list){
			Element event = (Element)e;
			String date = event.getChildText("Col8");
			DateTime startDateTime = myFormatter.parseDateTime(date);

			String enddate = event.getChildText("Col9");
			DateTime endDateTime = myFormatter.parseDateTime(enddate);

			String link = event.getChildText("Col2");
			String location = event.getChildText("Col15");
			String name = event.getChildText("Col1");
			
			CalendarEvent ce = new CalendarEvent(name, location, endDateTime, startDateTime, link);
			collection.add(ce);
		}	
		return collection;
	}
}