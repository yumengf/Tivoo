import org.joda.time.DateTime;


import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Text;


/*
 * CalendarEvent is the object Parser classes created.
 * It contains all desired information extracted from .xml file
 * Its variables are private and can be accessed through getter methods
 */
public class CalendarEvent {
	private String myEventName;
	private String myLocation;
	private String myLink;
	private DateTime myStartDate;
	private DateTime myEndDate;

	public CalendarEvent()
	{
		myEventName = null;
		myLocation = null;
		myStartDate = null;
		myEndDate = null;
		myLink = null;
	}

	public CalendarEvent(String name, String location, DateTime start, DateTime end, String link)
	{
		myEventName = name;
		myLocation = location;
		myStartDate = start;
		myEndDate = end;
		myLink = link;
	}

	public String getMyName() {
		return myEventName;
	}

	public void setMyName(String myName) {
		this.myEventName = myName;
	}
	
	public String getMyLink()
	{
		return myLink;
	}

	public void clear()
	{
		myEventName = null;
		myLocation = null;
		myStartDate = null;
		myEndDate = null;
		myLink = null;
	}
	public boolean isInTimeFrame(DateTime start, DateTime finish)
	{
		if (myStartDate.isAfter(start)){
            //End Date is less than or equal to finish date
            if (myEndDate.isBefore(finish)){
                    return true;
            }
		}
		return false;
	}
	public boolean hasKeyWord(String word)
	{
		if(myEventName.indexOf(word) != -1)
		{
			return true;
		}
		return false;
	}
	public int startDayOfWeek()
	{
		return myStartDate.getDayOfWeek();
	}
	public void appendInformation(Div div)
	{
		div.appendChild(new Text("Event Name: " + myEventName + "<br />"));
		div.appendChild(new Text("Event Location: " + myLocation + "<br />"));
		div.appendChild(new Text("Event Start Time: " + myStartDate.toString("EEEE dd MMMM, yyyy HH:mm:ssa") + "<br />"));
		div.appendChild(new Text("Event End Time: " + myEndDate.toString("EEEE dd MMMM, yyyy HH:mm:ssa") + "<br />"));
	}
	


}

//import org.joda.time.DateTime;
//
//
///*
// * CalendarEvent is the object Parser classes created.
// * It contains all desired information extracted from .xml file
// * Its variables are private and can be accessed through getter methods
// */
//public class CalendarEvent {
//	private String myEventName;
//	private String myLocation;
//	private String myLink;
//	private DateTime myStartDate;
//	private DateTime myEndDate;
//	
//	public CalendarEvent()
//	{
//		myEventName = null;
//		myLocation = null;
//		myStartDate = null;
//		myEndDate = null;
//		myLink = null;
//	}
//	
//	public CalendarEvent(String name, String location, DateTime start, DateTime end, String link)
//	{
//		setMyName(name);
//		setMyLocation(location);
//		setMyStartDate(start);
//		setMyEndDate(end);
//		setMyLink(link);
//	}
//
//	public String getMyName() {
//		return myEventName;
//	}
//
//	public void setMyName(String myName) {
//		this.myEventName = myName;
//	}
//
//	public String getMyLocation() {
//		return myLocation;
//	}
//
//	public void setMyLocation(String myLocation) {
//		this.myLocation = myLocation;
//	}
//
//	public DateTime getMyStartDate() {
//		return myStartDate;
//	}
//	
//	public DateTime getMyEndDate() {
//		return myEndDate;
//	}
//
//	public void setMyStartDate(DateTime start) {
//		this.myStartDate = start;
//	}
//	
//	public void setMyEndDate(DateTime end) {
//		this.myEndDate = end;
//	}
//
//	public String getMyLink() {
//		return myLink;
//	}
//
//	public void setMyLink(String myLink) {
//		this.myLink = myLink;
//	}
//	public void clear()
//	{
//		myEventName = null;
//		myLocation = null;
//		myStartDate = null;
//		myEndDate = null;
//		myLink = null;
//	}
//	
//
//}
