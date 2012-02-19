import java.io.IOException;
import java.util.*;
import org.jdom.JDOMException;

/*
 * A simple main which takes in a file, specifically, src/DukeCal.xml
 * and parses, filters and outputs an HTML file that shows relevant information
 */
public class ParserTester {
	
	public static void main(String args[]) {
		
		try {
			String file = "src/DukeCal.xml";
			Parser parser = new DukeCalParser(file);
			if(!parser.isThisKind())
			{
				parser = new NFLParser(file);
			}
			ArrayList<CalendarEvent> list = parser.parse();
			Process processor = new Process(list);
			list = processor.keyword("Lemur");
			
			 Output output = new Output(list);
			 output.outputFile();
				
			
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

}
