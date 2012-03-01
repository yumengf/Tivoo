package input;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/*
 * Determines which specific Parser subclass to instantiate.
 * myRoot is created and to be passed to the specific parser subclass
 * to avoid double parsing the input file
 */
public class ParserFactory {
	private Map<String, Class<?>> myMap;
	private Element myRoot;

	public ParserFactory() throws ClassNotFoundException {
		myMap = new HashMap<String, Class<?>>();
		// Class.forName is a static method in Class class
		myMap.put("document", Class.forName("input.NFLParser"));
		myMap.put("tv", Class.forName("input.TVParser"));
		myMap.put("events", Class.forName("input.DukeCalParser"));
		myMap.put("feed", Class.forName("input.GoogleParser"));
		myMap.put("dataroot", Class.forName("input.BballGameParser"));
	}

	/*
	 * returns a specific parser subclass based on the rootNode of the input xml
	 * file A non-static method because it cannot bypass constructor.
	 */
	public Parser getParser(String filename) throws JDOMException, IOException,
			InstantiationException, IllegalAccessException {
		File myFile = new File(filename);
		SAXBuilder myBuilder = new SAXBuilder();
		Document document = (Document) myBuilder.build(myFile);
		myRoot = document.getRootElement();

		Class<?> thisParser = myMap.get(myRoot.getName());
		return (Parser) thisParser.newInstance(); // require an empty
													// constructor
	}

	public Element getMyRoot() {
		return myRoot;
	}

}
