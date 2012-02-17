import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class DukeCalParser extends Parser{
		
	private File myFile;
	private SAXBuilder myBuilder;
	private DateTimeFormatter myFormatter = DateTimeFormat.forPattern("M/d/yyyy H:mm aa");
	
	public DukeCalParser(String filename) //main calls this, passes in a file
	{
		myFile = new File(filename);
		myBuilder = new SAXBuilder();
	}
	
	public boolean isThisKind()
	{
		Document document;
		try {
			
			document = (Document) myBuilder.build(myFile);
			Element rootNode = document.getRootElement();
			System.out.println(rootNode.getText());
			return rootNode.getText().equals("");
			
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<CalendarEvent> parse() throws JDOMException, IOException
	{
		ArrayList<CalendarEvent> collection = new ArrayList<CalendarEvent>();
		
		Document document = (Document) myBuilder.build(myFile);
		Element rootNode = document.getRootElement();
		List list = rootNode.getChildren("event");
		
		for(Object e : list)
		{
			Element event = (Element)e;
			
			Element startNode = event.getChild("start");
			String date = startNode.getChildText("shortdate") + " " + startNode.getChildText("time");
			DateTime startDateTime = myFormatter.parseDateTime(date);
			
			Element endNode = event.getChild("end");
			String enddate = endNode.getChildText("shortdate") + " " + startNode.getChildText("time");
			DateTime endDateTime = myFormatter.parseDateTime(enddate);
			
			String link = event.getChildText("link");
			
			String location = event.getChild("location").getChildText("address") + "\n" + event.getChild("location").getChildText("subaddress");
			
			String name = event.getChildText("summary");
			
			CalendarEvent ce = new CalendarEvent(name, location, endDateTime, startDateTime, link);
			
			collection.add(ce);
		}	
		System.out.println(collection.size());
		return collection;
	}
}
