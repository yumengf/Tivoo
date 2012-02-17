import java.io.IOException;
import java.util.ArrayList;

import org.jdom.JDOMException;


public abstract class Parser {
	public abstract boolean isThisKind();
	
	public abstract ArrayList<CalendarEvent> parse() throws JDOMException, IOException;
}
