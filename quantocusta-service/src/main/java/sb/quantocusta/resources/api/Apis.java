package sb.quantocusta.resources.api;

import java.util.HashMap;
import java.util.Map;

public class Apis {
	
	private static Map<String, Object> apis = new HashMap<String, Object>();
	
	public static void addApi(String name, Object api) {
		apis.put(name, api);
	}
	
	public static Object get(String name) {
		return apis.get(name);
	}

	public static <T> T get(Class<T> c, String name) {
		return (T) apis.get(name);
	}
	
}
