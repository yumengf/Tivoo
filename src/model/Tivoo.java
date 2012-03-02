package model;

import process.Process;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.joda.time.DateTime;

import input.CalendarEvent;
import input.Parser;
import input.ParserFactory;
import output.OutputFactory;

/*
 * A simple main which takes in a file, specifically, src/DukeCal.xml
 * and parses, filters and outputs an HTML file that shows relevant information
 */
public class Tivoo {
	public static void main(String args[]) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, Exception,
			IllegalArgumentException, NoSuchMethodException,
			InvocationTargetException {
		try {
			// file should be picked from GUI;
			String file = "src/NFLcalendar.xml";
			ParserFactory factory = new ParserFactory();
			Parser parser = factory.getParser(file);
			Element rootNode = factory.getMyRoot();

			ArrayList<CalendarEvent> list = parser.parse(rootNode);

			/*
			 * Test Case One:
			 */
			Process processor = new Process();
			ArrayList<Object> l = new ArrayList<Object>();
			l.add(new DateTime(2011, 12, 1, 0, 0));
			l.add(new DateTime(2011, 12, 31, 0, 0));		
			list = processor.filter("timeFrame", l, list);

			OutputFactory output = new OutputFactory(list);
			output.output("month", "");

			/*
			 * Test Case Two:
			 */
//			 Process processor = new Process();
//			 ArrayList<Object> l = new ArrayList<Object>();
//			 l.add("Giants");
//			 list = processor.filter("keepKeyword", l, list);
//			
//			 OutputFactory output = new OutputFactory(list);
//			 output.output("month","");
			
			
			/*
			 * Test Case Three:
			 */
//			 Process processor = new Process();
//			 ArrayList<Object> l = new ArrayList<Object>();
//			 l.add(new DateTime(2011, 12, 5, 0, 0));
//			 l.add(new DateTime(2011, 12, 11, 0 ,0));
//			 list = processor.filter("timeFrame", l, list);
//			
//			 OutputFactory output = new OutputFactory(list);
//			 output.output("week","");
			
			
			/*
			 * Test Case Four:
			 */
//			 Process processor = new Process();
//			 ArrayList<Object> l = new ArrayList<Object>();
//			 list = processor.filter("conflict", l, list);
//			
//			 OutputFactory output = new OutputFactory(list);
//			 output.output("sort","Conflict Events");

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
