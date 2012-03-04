package GUI;



import input.*;
import output.*;
import process.*;
//import input.ParserFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.jdom.Element;
import org.jdom.JDOMException;

public class TivooModel {
	private TivooViewer myView;// view and controller
	private ArrayList<CalendarEvent> myEvents;
	private File myFile;
	private ParserFactory pfactory;// = new ParserFactory();
	private FilterFactory fFactory;

	/**
	 * Initialize the model appropriately.
	 * @throws ClassNotFoundException 
	 */
	public TivooModel() throws ClassNotFoundException {
		myEvents = new ArrayList<CalendarEvent>();
		pfactory = new ParserFactory();
		fFactory = new FilterFactory();
	}

	/**
	 * Associate a view with this model.
	 * @param view is view that's notified when model
	 * changes
	 */
	public void addView(TivooViewer view) {
		myView = view;
	}
	public void loadAndParseFile(File file) throws JDOMException, IOException, InstantiationException, IllegalAccessException
	{
		myFile = file;
		//ParserFactory factory = new ParserFactory();
		Parser parser = pfactory.getParser(file);
		Element rootNode = pfactory.getMyRoot();

		ArrayList<CalendarEvent> list = parser.parse(rootNode);
		myEvents.addAll(list);
		myView.showMessage("File read and parsed");
		//File dir1 = new File(".");
		//File dir2 = new File("..");
//		try {
//			System.out.println("Current dir : " + dir1.getCanonicalPath());
//			System.out.println("Parent  dir : " + dir2.getCanonicalPath());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		//myView.update("../Output/Month91.htm");
	}
	public void filter(String s) throws SecurityException, IllegalArgumentException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException
	{
		Filter filter = fFactory.getFilter(s);
		ArrayList<Object> parameter = new ArrayList<Object>();
		if(s.equals("keyword")|s.equals("RemoveKeyword"))
		{
			String keyword = myView.getInput();
			parameter.add(keyword);
		}
		
		myEvents = filter.filter(parameter, myEvents);
		myView.showMessage("Events filtered");
	}
	
	public void monthView()
	{
		Output output = new MonthOutput(myEvents);
		output.outputFile("");
		myView.update("file:/C:/Users/Chris/workspace/Tivoo3/Output/MonthOutput.htm");
		myView.setHome("file:/C:/Users/Chris/workspace/Tivoo3/Output/MonthOutput.htm");
	}
	public void weekView()
	{
		Output output = new WeekOutput(myEvents);
		output.outputFile("");
		myView.update("file:/C:/Users/Chris/workspace/Tivoo3/Output/WeekOutput.htm");
		myView.setHome("file:/C:/Users/Chris/workspace/Tivoo3/Output/WeekOutput.htm");
	}


}
