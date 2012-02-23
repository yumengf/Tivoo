import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class FilterFactory {
	private Map<String, Class<?>> myMap; 
	
	public FilterFactory() throws ClassNotFoundException{
		myMap = new HashMap<String, Class<?>>(); 
		myMap.put("keyword", Class.forName("KeywordFilter")); 
		myMap.put("timeFrame", Class.forName("TimeFrameFilter")); 
	}
	
	public Filter getFilter(String commandName) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
		Class<?> thisFilter = myMap.get(commandName);  
		return (Filter) thisFilter.newInstance(); 
	}
}
