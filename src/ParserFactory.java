package Input;

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

	public ParserFactory() throws ClassNotFoundException{
		myMap = new HashMap<String, Class<?>>();
		myMap.put("", Class.forName("DukeCalParser")); //Class.forName is a static method in Class class
		myMap.put("document", Class.forName("NFLParser"));
		myMap.put("tv", Class.forName("TVParser"));
	}

	/*
	 * returns a specific parser subclass based on the rootNode of the input xml file
	 * A non-static method because it cannot bypass constructor.
	 */
	public Parser getParser(String filename) throws JDOMException, IOException, InstantiationException, IllegalAccessException{
		File myFile = new File(filename);
		SAXBuilder myBuilder = new SAXBuilder();
		Document document = (Document) myBuilder.build(myFile);
		myRoot = document.getRootElement();
<<<<<<< HEAD

		System.out.print(myRoot.getName());
		Class<?> thisParser = myMap.get(myRoot.getName());
		return (Parser) thisParser.newInstance(); //require an empty constructor, which we have!

=======
		
		System.out.print(myRoot.getName());
		Class<?> thisParser = myMap.get(myRoot.getName());
		return (Parser) thisParser.newInstance(); //require an empty constructor, which we have!
		
>>>>>>> a22fd2fa9e6f349dfe6755a246e9eb02c3360ea2
	}

	public Element getMyRoot() {
		return myRoot;
	}
<<<<<<< HEAD


}
=======
	
	
}
>>>>>>> a22fd2fa9e6f349dfe6755a246e9eb02c3360ea2
