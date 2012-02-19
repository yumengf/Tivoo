import org.joda.time.DateTime;


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
		setMyName(name);
		setMyLocation(location);
		setMyStartDate(start);
		setMyEndDate(end);
		setMyLink(link);
	}

	public String getMyName() {
		return myEventName;
	}

	public void setMyName(String myName) {
		this.myEventName = myName;
	}

	public String getMyLocation() {
		return myLocation;
	}

	public void setMyLocation(String myLocation) {
		this.myLocation = myLocation;
	}

	public DateTime getMyStartDate() {
		return myStartDate;
	}
	
	public DateTime getMyEndDate() {
		return myEndDate;
	}

	public void setMyStartDate(DateTime start) {
		this.myStartDate = start;
	}
	
	public void setMyEndDate(DateTime end) {
		this.myEndDate = end;
	}

	public String getMyLink() {
		return myLink;
	}

	public void setMyLink(String myLink) {
		this.myLink = myLink;
	}
	public void clear()
	{
		myEventName = null;
		myLocation = null;
		myStartDate = null;
		myEndDate = null;
		myLink = null;
	}
	

}
