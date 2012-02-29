package output;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;


import Tivoo.CalendarEvent;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Body;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.H1;
import com.hp.gagawa.java.elements.Html;
import com.hp.gagawa.java.elements.Text;

/*
 * Output is an abstract super class for all subclass Outputs.
 */

public abstract class Output {
	public abstract void outputFile(String string);
	
	/*
	 * Construct information of CalendarEvent object  in each detail event page, 
	 * return address of the specific page
	 */
	public String eventFile(String string, int i, int j, CalendarEvent cal) {
		Html html = new Html();
		Body body = new Body().setBgcolor("grey");	
		H1 head = new H1().appendChild(new Text("Detail Information about the Event:")).setAlign("center");
		body.appendChild(head);
		Div div = new Div();
		cal.appendInformation(div);  // Append eventName, date, location and times to div.
		A link = new A().setHref("http://" + cal.getMyLink()).setTarget("_blank").appendChild(new Text("Link for Detail Page"));			//attach link page
		
		div.appendChild(link);
		body.appendChild(div);
		html.appendChild(body);
				
		String preAddress = string + i + j + ".htm";	//construct filename for specific event
		writeInFile(html, "Output/" + preAddress);		
		return preAddress;
	}

	/*
	 * Take in html object and the file address, write html file into it.
	 */
	public void writeInFile(Html html, String address) {		
		try {
			File file = new File(address);		
	        PrintWriter out = new PrintWriter(new FileOutputStream(file));
	        out.println(html.write());								//write into file.
	        out.close();
			} catch (FileNotFoundException e) {
                e.printStackTrace();
        }
	}
}