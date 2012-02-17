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


public class NFLParser extends Parser{

	private File myFile;
	private SAXBuilder myBuilder;
	private DateTimeFormatter myFormatter = DateTimeFormat.forPattern("yyyy-MM-dd H:mm:ss");

	public NFLParser(String filename) //main calls this, passes in a file
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