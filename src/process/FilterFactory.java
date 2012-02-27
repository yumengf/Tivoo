package process;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;



public class FilterFactory {
	private Map<String, Class<?>> myMap; 
	
	public FilterFactory() throws ClassNotFoundException{
		
		myMap = new HashMap<String, Class<?>>(); 
		myMap.put("keyword", Class.forName("process.KeywordFilter"));
		myMap.put("conflict", Class.forName("process.ConflictFilter"));
		
		myMap.put("RemoveKeyword", Class.forName("process.RemoveKeywordFilter")); 
		myMap.put("name reverse order", Class.forName("process.NameReverseFilter"));
		myMap.put("nameOrder", Class.forName("process.NameOrderFilter"));
		myMap.put("timeFrame", Class.forName("process.TimeFrameFilter")); 
		
		myMap.put("start in order", Class.forName("process.StartTimeFilter")); 
		myMap.put("start reverse order", Class.forName("process.StartReverseFilter")); 
		myMap.put("end in order", Class.forName("process.EndTimeFilter")); 
		myMap.put("end reverse order", Class.forName("process.EndReverseFilter")); 
		myMap.put("ClassSpecific", Class.forName("process.ClassSpecificFilter"));
		
	}
	
	public Filter getFilter(String commandName) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		Class<?> thisFilter = myMap.get(commandName);  
		return (Filter) thisFilter.newInstance(); 
	}
}
