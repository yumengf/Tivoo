package Input;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;
import org.jdom.JDOMException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import Tivoo.CalendarEvent;

/*
 * DukeCalParser class extends the abstract class Parser.java
 * It parses DukeCal.xml and return and arraylist of Calendar event
 * with relevant information: start date, end date, link, location, and summary
 */
public class TVParser implements Parser{

	private DateTimeFormatter myFormatter = DateTimeFormat.forPattern("yyyyMMddHHmmss Z");
	
	public TVParser() {
	}
	
	/*
	 * parse() returns an ArrayList of CalendarEvent.java which contains
	 * the information of start date, end date, link, location, and summary
	 * (non-Javadoc)
	 * @see Parser#parse()
	 */
	public ArrayList<CalendarEvent> parse(Element rootNode) throws JDOMException, IOException
	{
		
		ArrayList<CalendarEvent> collection = new ArrayList<CalendarEvent>();
		List<?> list = rootNode.getChildren("programme");
		List<?> channelList = rootNode.getChildren("channel");
		
		for(Object e : list)
		{
			Element event = (Element)e;
			
			
			String date = event.getAttributeValue("start");
			DateTime startDateTime = myFormatter.parseDateTime(date);
			
			
			String enddate = event.getAttributeValue("stop");
			DateTime endDateTime = myFormatter.parseDateTime(enddate);
			
			String link = "";//event.getChildText("link");
			
			String channel = event.getAttributeValue("channel");
			for(Object o : channelList)
			{
				Element possibleChannel = (Element)o;
				if(possibleChannel.getAttributeValue("id").equals(channel))
				{
					List<?> nameList = possibleChannel.getChildren("display-name");
					Element found = (Element)nameList.get(4);
					channel = found.getText();
					break;
				}
			}
			
			String name = event.getChildText("title");
			
			CalendarEvent ce = new CalendarEvent(name, channel, endDateTime, startDateTime, link);
			
			collection.add(ce);
		}	
		return collection;
	}
}
