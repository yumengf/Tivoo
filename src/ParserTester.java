import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java.util.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jdom.JDOMException;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParserTester {

	/*private static EventListFactory eventFactory;*/
	
	
	public static void main(String args[]) {
		
		try {
			
			NFLParser parser = new NFLParser("src/NFLcalender.xml");
			ArrayList<CalendarEvent> list = parser.parseDuke();
			Process processor = new Process(list);
			list = processor.keyword("Jets");
			
			 Output output = new Output(list);
			 output.outputFile();
				
			
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

}
