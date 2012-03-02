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
			String file = "src/googlecal.xml";
			ParserFactory factory = new ParserFactory();
			Parser parser = factory.getParser(file);
			Element rootNode = factory.getMyRoot();

			ArrayList<CalendarEvent> list = parser.parse(rootNode);
//			System.out.println(list.size());
			/*
			 * Test Case One:
			 */
			Process processor = new Process();
			ArrayList<Object> l = new ArrayList<Object>();
			l.add(new DateTime(2011, 9, 1, 0, 0));
			l.add(new DateTime(2011, 9, 30, 0, 0));
			list = processor.filter("timeFrame", l, list);

			OutputFactory output = new OutputFactory(list);
			output.output("month", "");

			/*
			 * Test Case Two:
			 */
			// Process processor = new Process();
			// ArrayList<Object> l = new ArrayList<Object>();
			// l.add(new DateTime(2011, 12, 2, 0, 0));
			// l.add(new DateTime(2011, 12, 3, 0 ,0));
			// list = processor.filter("timeFrame", l, list);
			//
			// OutputFactory output = new OutputFactory(list);
			// output.output("day","");
			
			
			/*
			 * Test Case Three:
			 */
			// Process processor = new Process();
			// ArrayList<Object> l = new ArrayList<Object>();
			// l.add(new DateTime(2011, 12, 1, 0, 0));
			// l.add(new DateTime(2011, 12, 7, 0 ,0));
			// list = processor.filter("timeFrame", l, list);
			//
			// OutputFactory output = new OutputFactory(list);
			// output.output("week","");
			
			
			/*
			 * Test Case Four:
			 */
			// Process processor = new Process();
			// ArrayList<Object> l = new ArrayList<Object>();
			// list = processor.filter("conflict", l, list);
			//
			// OutputFactory output = new OutputFactory(list);
			// output.output("sort","Conflict Events");

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
