package input;

import java.util.ArrayList;
import org.joda.time.DateTime;


public class TVshow extends CalendarEvent{
	private ArrayList<String> myActors;
	
	public TVshow(String name, String location, DateTime start, DateTime end, String link, ArrayList<String> actors)
	{
		myEventName = name;
		myLocation = location;
		myStartDate = start;
		myEndDate = end;
		myLink = link;
		myActors = actors;
	}
	public TVshow(String name, String location, DateTime start, DateTime end, String link)
	{
		myEventName = name;
		myLocation = location;
		myStartDate = start;
		myEndDate = end;
		myLink = link;
		myActors = new ArrayList<String>();
	}
	
	public boolean isTVShow()
	{
		return true;
	}
	public boolean hasActor(String actor)
	{
		for(String s: myActors)
		{
			if(s.equals(actor))
			{
				return true;
			}
		}
		return false;
	}

}
