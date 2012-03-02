package input;

import java.io.IOException;
import java.util.ArrayList;

import org.jdom.Element;
import org.jdom.JDOMException;

/*
 * Parser is an interface for all subclass Parsers for individual .xml files
 */
public interface Parser {
	public ArrayList<CalendarEvent> parse(Element rootNode)
			throws JDOMException, IOException;
}
