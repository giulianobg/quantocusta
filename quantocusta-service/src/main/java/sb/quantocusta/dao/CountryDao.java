package sb.quantocusta.dao;

import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;

import sb.quantocusta.api.Country;

import com.mongodb.DB;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class CountryDao {
	
//	private DB db;
	private JacksonDBCollection<Country, String> coll;

	public CountryDao(DB db) {
//		this.db = db;
		coll = JacksonDBCollection.wrap(db.getCollection("countries"), Country.class, String.class);
	}
	
	public Country findById(String id) {
		return coll.findOneById(id);
	}
	
	public Country findBy3rdId(String id) {
		return coll.findOne(DBQuery.is("code", id));
	}

}
