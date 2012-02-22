import java.io.IOException;
import java.util.*;

import org.jdom.Element;
import org.jdom.JDOMException;

/*
 * A simple main which takes in a file, specifically, src/DukeCal.xml
 * and parses, filters and outputs an HTML file that shows relevant information
 */
public class Tivoo {
	public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			String file = "src/NFLcalendar.xml";
			ParserFactory factory = new ParserFactory();
			Parser parser = factory.getParser(file);
			Element rootNode = factory.getMyRoot();
			
			ArrayList<CalendarEvent> list = parser.parse(rootNode);
			Process processor = new Process(list);
			list = processor.keyword("49ers");
			
			Output output = new Output(list);
			output.outputFile();
			 
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
