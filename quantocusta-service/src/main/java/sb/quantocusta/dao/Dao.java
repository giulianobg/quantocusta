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
	
	public T findBy3rdId(String id) {return null;}
	
	public T insert(T obj) {
		LOG.debug("Inserting object '" + obj + "' ...");
		T saved = coll.insert(obj).getSavedObject();
		if (saved != null) {
			LOG.debug("Object '" + obj + "' was inserted.");
		}
		return saved;
	}
	
	public T update(T obj) {
		LOG.debug("Saving object '" + obj + "' ...");
		T saved = coll.save(obj).getSavedObject();
		if (saved != null) {
			LOG.debug("Object '" + obj + "' was inserted.");
		}
		return saved;
	}

}
