import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.Head;
import com.hp.gagawa.java.elements.Html;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Title;
import com.hp.gagawa.java.elements.P;

public class ReadXMLFile {
	public static void main(String[] args) {

		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("dukecal.xml");

		try {

			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			List<?> list = rootNode.getChildren("event"); // a list of 'event'

			// Make HTML using gagawa
			Html html = new Html();
			Head head = new Head();

			html.appendChild(head);

			Title title = new Title();
			title.appendChild(new Text("Duke Calendar Prototype"));
			head.appendChild(title);

			Body body = new Body();

			html.appendChild(body);

			H1 h1 = new H1();
			h1.appendChild(new Text("Duke Calender - Tivoo project"));
			body.appendChild(h1);

			// go through the list of events, process each event
			for (int i = 0; i < list.size(); i++) {

				Element node = (Element) list.get(i); // get each individual
														// node of event

				Element startNode = node.getChild("start");

				String date = startNode.getChildText("year")
						+ startNode.getChildText("twodigitmonth")
						+ startNode.getChildText("twodigitday");

				DateTimeFormatter format = DateTimeFormat
						.forPattern("yyyyMMdd");

				DateTime dateTime = format.parseDateTime(date);

				if (dateTime.isBeforeNow()) {
					P myP = new P();
					myP.appendChild(new Text(date
							+ node.getChildText("summary")));

					A myLink = new A();
					myLink.setHref(node.getChildText("link"));

					myP.appendChild(myLink);

					body.appendChild(myP);

				}
			}

			// System.out.println( html.write() ); //better if can print to an
			// HTML file...
			Writer w = new OutputStreamWriter(new FileOutputStream(
					"output filtered Duke Calender.html"));
			w.write(html.write());

		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		}
	}

}