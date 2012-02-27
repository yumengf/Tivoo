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
 * GoogleParser class extends the abstract class Parser.java
 * It parses DukeCal.xml and return and arraylist of Calendar event
 * with relevant information: start date, end date, link, location, and summary
 */
public class GoogleParser implements Parser{
	private DateTimeFormatter myFormatter1 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss zzz");
	private DateTimeFormatter myFormatter2 = DateTimeFormat.forPattern("EEE MMM d, yyyy h:mmaa");
	private DateTimeFormatter myFormatter3 = DateTimeFormat.forPattern("h:maa");
	
	public GoogleParser() {
	}
	
	/*
	 * parse() returns an ArrayList of CalendarEvent.java which contains
	 * the information of start date, end date, link, location, and summary
	 * (non-Javadoc)
	 * @see Parser#parse()
	 */
	public ArrayList<CalendarEvent> parse(Element rootNode) throws JDOMException, IOException{
		ArrayList<CalendarEvent> collection = new ArrayList<CalendarEvent>();
		List<?> list = rootNode.getChildren("entry");
		
		for(Object e : list){
			Element event = (Element)e;
			
			String information = event.getChildText("summary");
			int start = information.indexOf("start: ") + 7;
			
			int endOfStart = information.indexOf(" <br>");
			int end = information.indexOf("Duration: ") + 10;
			int endDuration = Integer.parseInt(information.substring(end, end + 4));
			
			DateTime startDateTime, endDateTime;
			
			
			if(start == -1)
			{
				start = information.indexOf("When: ") + 6;
				endOfStart = information.indexOf(" to ");
				end = endOfStart + 4;
				int endOfEnd = information.indexOf("&nbsp;");
				
				startDateTime = myFormatter2.parseDateTime(information.substring(start, endOfStart));
				
				DateTime endTime = myFormatter3.parseDateTime(information.substring(end, endOfEnd));
				endDateTime = new DateTime(startDateTime.getYear(), startDateTime.getMonthOfYear(), startDateTime.getDayOfMonth(), endTime.getHourOfDay(), endTime.getMinuteOfHour());		
				
			} else {
				
			startDateTime = myFormatter1.parseDateTime(information.substring(start, endOfStart));
			endDateTime = startDateTime.plusSeconds(endDuration);
			
			}
			
			
			//Get location
			String location = "No Location Provided!";
			if(information.indexOf("Where") != -1)
			{
				int s = information.indexOf("Where") + 7;
				int eee = information.indexOf(" <br>", s); //sorry about the name, I am frustrated..
				location = information.substring(s, eee);
			}
			
			
			
			String name = event.getChildText("title");
			
			String link = "No Link Provided!";
			if(event.getChild("link").getAttributeValue("rel").equals("alternate"))
			{
				link = event.getChild("link").getAttributeValue("href");	
			}
			
			
			CalendarEvent ce = new CalendarEvent(name, location, endDateTime, startDateTime, link);
			collection.add(ce);
		}	
		return collection;
	}
}
