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
 * BballGameParser class extends the abstract class Parser.java
 * It parses DukeCal.xml and return and arraylist of Calendar event
 * with relevant information: start date, end date, link, location, and summary
 */
public class BballGameParser implements Parser {
	private DateTimeFormatter myFormatter = DateTimeFormat
			.forPattern("M/d/yyyy hh:mm:ss aa");

	public BballGameParser() {
	}

	/*
	 * parse() returns an ArrayList of CalendarEvent.java which contains the
	 * information of start date, end date, link, location, and summary
	 * (non-Javadoc)
	 * 
	 * @see Parser#parse()
	 */
	public ArrayList<CalendarEvent> parse(Element rootNode)
			throws JDOMException, IOException {
		ArrayList<CalendarEvent> collection = new ArrayList<CalendarEvent>();
		List<?> list = rootNode.getChildren("Calendar");

		for (Object e : list) {
			Element event = (Element) e;

			String date = event.getChildText("StartDate") + " "
					+ event.getChildText("StartTime");
			DateTime startDateTime = myFormatter.parseDateTime(date);

			String enddate = event.getChildText("EndDate") + " "
					+ event.getChildText("EndTime");
			DateTime endDateTime = myFormatter.parseDateTime(enddate);

			String location = event.getChildText("Location");
			String name = event.getChildText("Subject");

			String link = event.getChildText("Description");

			CalendarEvent ce = new CalendarEvent(name, location, endDateTime,
					startDateTime, link);
			collection.add(ce);
		}
		return collection;
	}

}
