import java.util.Date;


public class CalendarEvent {
	private String myName;
	private String myLocation;
	private Date myDate;
	private String myLink;
	
	public CalendarEvent(String name, String location, Date date, String link)
	{
		setMyName(name);
		setMyLocation(location);
		setMyDate(date);
		setMyLink(link);
	}

	public String getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName = myName;
	}

	public String getMyLocation() {
		return myLocation;
	}

	public void setMyLocation(String myLocation) {
		this.myLocation = myLocation;
	}

	public Date getMyDate() {
		return myDate;
	}

	public void setMyDate(Date myDate) {
		this.myDate = myDate;
	}

	public String getMyLink() {
		return myLink;
	}

	public void setMyLink(String myLink) {
		this.myLink = myLink;
	}
	

}
