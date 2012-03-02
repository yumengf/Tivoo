package process;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/*
 * called by process class to return the correct filter need by using reflection!
 */

public class FilterFactory {
	private Map<String, Class<?>> myMap;

	public FilterFactory() throws ClassNotFoundException {
		myMap = new HashMap<String, Class<?>>();
		myMap.put("keepKeyword", Class.forName("process.KeywordFilter"));
		myMap.put("removeKeyword", Class.forName("process.RemoveKeywordFilter"));
		myMap.put("startInOrder", Class.forName("process.StartTimeFilter"));
		myMap.put("startReverseOrder", Class.forName("process.StartReverseFilter"));
		myMap.put("endInOrder", Class.forName("process.EndTimeFilter"));
		myMap.put("endReverseOrder", Class.forName("process.EndReverseFilter"));
		myMap.put("nameOrder", Class.forName("process.NameOrderFilter"));
		myMap.put("nameReverseOrder", Class.forName("process.NameReverseFilter"));
		myMap.put("conflict", Class.forName("process.ConflictFilter"));
		myMap.put("timeFrame", Class.forName("process.TimeFrameFilter"));
		myMap.put("ClassSpecific", Class.forName("process.ClassSpecificFilter"));

	}

	public Filter getFilter(String commandName) throws InstantiationException,
			IllegalAccessException, SecurityException, NoSuchMethodException,
			IllegalArgumentException, InvocationTargetException {
		Class<?> thisFilter = myMap.get(commandName);
		return (Filter) thisFilter.newInstance();
	}
}
