package sb.quantocusta.dao;

import org.mongojack.JacksonDBCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Giuliano Griffante
 *
 * @param <T>
 */
public abstract class Dao<T> {
	
	static Logger LOG = LoggerFactory.getLogger(Dao.class);
	
	protected JacksonDBCollection<T, String> coll;
	
	public Dao(JacksonDBCollection<T, String> coll) {
		this.coll = coll;
	}
	
	public T findById(String id) {
		return coll.findOneById(id);
	}
	
	public abstract T findBy3rdId(String id);
	
	public T insert(T obj) {
		LOG.debug("Inserting object '" + obj + "' ...");
		String savedId = coll.insert(obj).getSavedId();
		if (savedId != null) {
			LOG.debug("Object '" + obj + "' was inserted.");
		}
		return findById(savedId);
	}
	
	public T update(T obj) {
//		LOG.debug("Saving object '" + obj + "' ...");
		return coll.save(obj).getSavedObject();
	}

}
