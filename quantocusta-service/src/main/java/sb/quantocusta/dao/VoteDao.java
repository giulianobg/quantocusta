package sb.quantocusta.dao;

import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;

import sb.quantocusta.api.Vote;

import com.mongodb.DB;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class VoteDao extends Dao<Vote> {
	
	public VoteDao(DB db) {
		super(JacksonDBCollection.wrap(db.getCollection("votes"), Vote.class, String.class));
	}
	
	public Vote findBy3rdId(String id) {
		return null;
	}
	
	public Vote find(String idVenue, String idUser, String kind) {
		return coll.findOne(DBQuery.is("idVenue", idVenue).is("idUser", idUser).is("kind", kind));
	}
	
}
