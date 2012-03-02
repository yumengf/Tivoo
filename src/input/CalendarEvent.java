package input;

import org.joda.time.DateTime;

import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Text;

/*
 * CalendarEvent is the object Parser classes created.
 * It contains all desired information extracted from .xml file
 * Its variables are private and can be accessed through getter methods
 */
public class CalendarEvent {
	// protected because TVshow is extending this class and need to use the
	// variables
	protected String myEventName;
	protected String myLocation;
	protected String myLink;
	protected DateTime myStartDate;
	protected DateTime myEndDate;
	protected boolean isRecurring;

	public CalendarEvent() {
		myEventName = null;
		myLocation = null;
		myStartDate = null;
		myEndDate = null;
		myLink = null;
		isRecurring = false;
	}
	
	public CalendarEvent(String name, String location, DateTime start,
			DateTime end, String link) {
		this(name, location, start, end, link, false);
	}

	public CalendarEvent(String name, String location, DateTime start,
			DateTime end, String link, boolean rec) {
		myEventName = name;
		myLocation = location;
		myStartDate = start;
		myEndDate = end;
		myLink = link;
		isRecurring = rec;
	}

	public String getMyName() {
		return myEventName;
	}

	public void setMyName(String myName) {
		this.myEventName = myName;
	}

	public String getMyLink() {
		return myLink;
	}

	public DateTime getMyStartTime() {
		return myStartDate;
	}

	public DateTime getMyEndTime() {
		return myEndDate;
	}

	public void clear() {
		myEventName = null;
		myLocation = null;
		myStartDate = null;
		myEndDate = null;
		myLink = null;
	}

	public boolean isInTimeFrame(DateTime start, DateTime finish) {
		if (myStartDate.isAfter(start)) {
			// End Date is less than or equal to finish date
			if (myEndDate.isBefore(finish)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasKeyWord(String word) {
		if (myEventName.indexOf(word) != -1) {
			return true;
		}
		if (myLocation.indexOf(word) != -1) {
			return true;
		}
		if (myLink.indexOf(word) != -1) {
			return true;
		}
		return false;
	}

	public int startDayOfWeek() {
		return myStartDate.getDayOfWeek();
	}

	public void appendInformation(Div div) {
		div.appendChild(new Text("Event Name: " + myEventName + "<br />"));
		div.appendChild(new Text("Event Location: " + myLocation + "<br />"));
		div.appendChild(new Text("Event Start Time: "
				+ myStartDate.toString("dd MMMM, yyyy HH:mm:ss")
				+ "<br />"));
		div.appendChild(new Text("Event End Time: "
				+ myEndDate.toString("dd MMMM, yyyy HH:mm:ss") + "<br />"));
	}

	public String toString() {
		String res = "Name: " + myEventName + "\n" + "Location: " + myLocation
				+ "\n" + "Link: " + myLink + "\n" + "Start Date: "
				+ myStartDate.toString() + "\n";
		return res;
	}

}
