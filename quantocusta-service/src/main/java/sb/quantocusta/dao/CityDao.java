package sb.quantocusta.dao;

import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;

import sb.quantocusta.api.City;

import com.mongodb.DB;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class CityDao extends Dao<City> {
	
//	private DB db;
//	private JacksonDBCollection<City, String> coll;

	public CityDao(DB db) {
		super(JacksonDBCollection.wrap(db.getCollection("cities"), City.class, String.class));
//		this.db = db;
//		coll = JacksonDBCollection.wrap(db.getCollection("cities"), City.class, String.class);
	}
	
	public City findById(String id) {
		return coll.findOneById(id);
	}
	
	public City findBy3rdId(String id) {
		return coll.findOne(DBQuery.is("name", id));
	}
	
	public City insert(City city) {
		String savedId = coll.insert(city).getSavedId();
		return findById(savedId);
	}

}
