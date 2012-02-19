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

/*
 * NFLParser class extends the abstract class Parser.java
 * It parses NFLcalendar.xml and return and arraylist of Calendar event
 * with relevant information: start date, end date, link, location, and summary
 */
public class NFLParser extends Parser{

	private File myFile;
	private SAXBuilder myBuilder;
	private DateTimeFormatter myFormatter = DateTimeFormat.forPattern("yyyy-MM-dd H:mm:ss");

	public NFLParser(String filename) 
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
			return rootNode.getText().equals("document");

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * parse() returns an ArrayList of CalendarEvent.java which contains
	 * the information of start date, end date, link, location, and summary
	 * (non-Javadoc)
	 * @see Parser#parse()
	 */
	public ArrayList<CalendarEvent> parse() throws JDOMException, IOException
	{
		ArrayList<CalendarEvent> collection = new ArrayList<CalendarEvent>();

		Document document = (Document) myBuilder.build(myFile);
		Element rootNode = document.getRootElement();
		List list = rootNode.getChildren("row");

		for(Object e : list)
		{
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