package sb.quantocusta.dao;

import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;

import sb.quantocusta.api.Venue;

import com.mongodb.DB;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class VenueDao extends Dao<Venue> {

	public VenueDao(DB db) {
		super(JacksonDBCollection.wrap(db.getCollection("venues"), Venue.class, String.class));
	}
	
	public Venue findBy3rdId(String id) {
		return coll.findOne(DBQuery.is("id_foursquare", id));
	}

}
