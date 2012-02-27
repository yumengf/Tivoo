package process;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;



public class FilterFactory {
	private Map<String, Class<?>> myMap; 
	
	public FilterFactory() throws ClassNotFoundException{
		
		myMap = new HashMap<String, Class<?>>(); 
		myMap.put("keyword", Class.forName("KeywordFilter"));
		myMap.put("conflict", Class.forName("ConflictFilter"));
		
		myMap.put("RemoveKeyword", Class.forName("RemoveKeywordFilter")); 
		myMap.put("name reverse order", Class.forName("NameReverseFilter"));
		myMap.put("nameOrder", Class.forName("NameOrderFilter"));
		myMap.put("timeFrame", Class.forName("TimeFrameFilter")); 
		
		myMap.put("start in order", Class.forName("StartTimeFilter")); 
		myMap.put("start reverse order", Class.forName("StartReverseFilter")); 
		myMap.put("end in order", Class.forName("EndTimeFilter")); 
		myMap.put("end reverse order", Class.forName("EndReverseFilter")); 
		myMap.put("ClassSpecific", Class.forName("ClassSpecificFilter"));
		
	}
	
	public Filter getFilter(String commandName) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		Class<?> thisFilter = myMap.get(commandName);  
		return (Filter) thisFilter.newInstance(); 
	}
}
