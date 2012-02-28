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
 * DukeCalParser class extends the abstract class Parser.java
 * It parses DukeCal.xml and return and arraylist of Calendar event
 * with relevant information: start date, end date, link, location, and summary
 */
public class DukeCalParser implements Parser {
	private DateTimeFormatter myFormatter = DateTimeFormat
			.forPattern("M/d/yyyy h:mm aa");

	public DukeCalParser() {
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
		List<?> list = rootNode.getChildren("event");

		for (Object e : list) {
			Element event = (Element) e;
			Element startNode = event.getChild("start");
			String date = startNode.getChildText("shortdate") + " "
					+ startNode.getChildText("time");
			DateTime startDateTime = myFormatter.parseDateTime(date);

			Element endNode = event.getChild("end");
			String enddate = endNode.getChildText("shortdate") + " "
					+ startNode.getChildText("time");
			DateTime endDateTime = myFormatter.parseDateTime(enddate);

			String link = event.getChildText("link");
			String location = event.getChild("location")
					.getChildText("address")
					+ "\n"
					+ event.getChild("location").getChildText("subaddress");
			String name = event.getChildText("summary");

			CalendarEvent ce = new CalendarEvent(name, location, endDateTime,
					startDateTime, link);
			collection.add(ce);
		}
		return collection;
	}
}
