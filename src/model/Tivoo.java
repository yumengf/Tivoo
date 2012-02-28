package Tivoo;

import process.Process;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import org.jdom.Element;
import org.jdom.JDOMException;

import Input.Parser;
import Input.ParserFactory;
import Output.OutputFactory;

/*
 * A simple main which takes in a file, specifically, src/DukeCal.xml
 * and parses, filters and outputs an HTML file that shows relevant information
 */
public class Tivoo {
	public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, Exception, IllegalArgumentException, NoSuchMethodException, InvocationTargetException {
		try {
			//file should be picked from GUI;
			String file = "src/NFLcalendar.xml";
			ParserFactory factory = new ParserFactory();
			Parser parser = factory.getParser(file);
			Element rootNode = factory.getMyRoot();

			ArrayList<CalendarEvent> list = parser.parse(rootNode);

			Process processor = new Process();
			ArrayList<Object> l = new ArrayList<Object>();
			list = processor.filter("name reverse order", l, list);
			
			//Output type to be determined by GUI
			//TODO: still have to handle edge case where nothing was filtered.
			OutputFactory output = new OutputFactory(list);
			output.output("sort","Name Reverse Order");
			
//			For the purpose of code testing:
//			Output output = new DayOutput(list);
//			output.outputFile("");
//			
//	 		Output output = new WeekOutput(list);
//			output.outputFile("");
//	
//			Output output = new SortListOutput(list);
//			output.outputFile("DaySort");
//	OR           output.outputFile("StartTimeSort");
//	OR           output.outputFile("EndTimeSort");

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
