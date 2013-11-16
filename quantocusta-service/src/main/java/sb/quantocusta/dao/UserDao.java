package sb.quantocusta.dao;

import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;

import sb.quantocusta.api.User;

import com.mongodb.DB;

/**
 * 
 * @author Giuliano Griffante
 */
public class UserDao extends Dao<User> {

	public UserDao(DB db) {
		super(JacksonDBCollection.wrap(db.getCollection("users"), User.class, String.class));
	}

	public User findBy3rdId(String id) {
		return coll.findOne(DBQuery.is("thirdyId", id));
	}

}
