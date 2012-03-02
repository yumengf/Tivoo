package input;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/*
 * GoogleParser class extends the abstract class Parser.java
 * It parses DukeCal.xml and return and arraylist of Calendar event
 * with relevant information: start date, end date, link, location, and summary
 */
public class GoogleParser implements Parser {
	private DateTimeFormatter myFormatter1 = DateTimeFormat
			.forPattern("yyyy-MM-dd HH:mm:ss");
	private DateTimeFormatter myFormatter2 = DateTimeFormat
			.forPattern("EEE MMM d, yyyy h:mmaa");
	private DateTimeFormatter myFormatter5 = DateTimeFormat
			.forPattern("EEE MMM d, yyyy haa");
	private DateTimeFormatter myFormatter6 = DateTimeFormat
			.forPattern("EEE MMM d, yyyy");
	private DateTimeFormatter myFormatter3 = DateTimeFormat.forPattern("h:maa");
	private DateTimeFormatter myFormatter4 = DateTimeFormat.forPattern("haa");

	public GoogleParser() {
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
		String url = "http://www.w3.org/2005/Atom";
		Namespace ns = Namespace.getNamespace(url);
		List<?> list = rootNode.getChildren("entry", ns);
		for (Object e : list) {
			Element event = (Element) e;

			String information = event.getChildText("summary", ns);
			int start = 0;

			int endOfStart = 0;
			int end = 0;
			int endDuration = 0;
			int endOfEnd = 0;
			DateTime startDateTime = new DateTime();
			DateTime endDateTime = new DateTime();
			boolean noEnd = false;

			if (information.indexOf("start: ") == -1) {
				try {
					start = information.indexOf("When: ") + 6;
					endOfStart = information.indexOf(" to ");
					end = endOfStart + 4;
					endOfEnd = information.indexOf("&nbsp;");

					startDateTime = myFormatter2.parseDateTime(information
							.substring(start, endOfStart));
				} catch (IllegalArgumentException err) {
					startDateTime = myFormatter5.parseDateTime(information
							.substring(start, endOfStart));
				} catch (StringIndexOutOfBoundsException eeee) {
					endOfStart = information.indexOf("<br>");

					startDateTime = myFormatter6.parseDateTime(information
							.substring(start, endOfStart));
					noEnd = true;
				}
				if (!noEnd) {
					try {
						DateTime endTime = myFormatter3
								.parseDateTime(information.substring(end,
										endOfEnd));
						endDateTime = new DateTime(startDateTime.getYear(),
								startDateTime.getMonthOfYear(),
								startDateTime.getDayOfMonth(),
								endTime.getHourOfDay(),
								endTime.getMinuteOfHour());
					} catch (IllegalArgumentException err) {
						DateTime endTime = myFormatter4
								.parseDateTime(information.substring(end,
										endOfEnd));
						endDateTime = new DateTime(startDateTime.getYear(),
								startDateTime.getMonthOfYear(),
								startDateTime.getDayOfMonth(),
								endTime.getHourOfDay(),
								endTime.getMinuteOfHour());
					}
				}
			} else {

				start = information.indexOf("start: ") + 7;

				endOfStart = information.indexOf(" EDT");
				end = information.indexOf("Duration: ") + 10;
				endDuration = Integer.parseInt(information.substring(end,
						end + 4));
				if (endOfStart == -1)
					endOfStart = information.indexOf("\n<br>");
				startDateTime = myFormatter1.parseDateTime(information
						.substring(start, endOfStart));
				endDateTime = startDateTime.plusSeconds(endDuration);

			}

			String location = "No Location Provided!";
			if (information.indexOf("Where") != -1) {
				int s = information.indexOf("Where") + 7;
				int eee = information.indexOf("\n<br>", s);

				if (s == -6)
					location = "";
				else
					location = information.substring(s, eee);
			}

			String name = event.getChildText("title", ns);

			String link = "No Link Provided!";
			if (event.getChild("link", ns).getAttributeValue("rel")
					.equals("alternate")) {
				link = event.getChild("link", ns).getAttributeValue("href");
			}

			CalendarEvent ce = new CalendarEvent(name, location, endDateTime,
					startDateTime, link);

			collection.add(ce);
		}
		return collection;
	}
}
