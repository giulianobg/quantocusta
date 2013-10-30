package sb.quantocusta.dao;

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
		return findById(id);
	}
	
}
