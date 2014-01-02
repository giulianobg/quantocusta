package sb.quantocusta.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class Daos {
	
	private static Map<String, Object> daos = new HashMap<String, Object>();
	
	public static void addDao(Object api) {
		daos.put(api.getClass().toString(), api);
	}
	
	public static Object get(String name) {
		return daos.get(name);
	}

	public static <T> T get(Class<T> c) {
		return (T) daos.get(c.toString());
	}
	
}
