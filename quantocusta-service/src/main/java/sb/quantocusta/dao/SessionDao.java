package sb.quantocusta.dao;

import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;

import sb.quantocusta.api.temp.Session;

import com.mongodb.DB;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class SessionDao extends Dao<Session> {

	public SessionDao(DB db) {
		super(JacksonDBCollection.wrap(db.getCollection("session"), Session.class, String.class));
	}
	
	public Session find(String accessToken) {
		return coll.findOne(DBQuery.is("access_token", accessToken));
	}
	
//	public List<Session> findAll(String accessToken) {
//		List<Session> sessions = new ArrayList<Session>();
//		return coll.f
//	}
	
	public void invalidate(String accessToken) {
//		coll. TODO
	}

}
