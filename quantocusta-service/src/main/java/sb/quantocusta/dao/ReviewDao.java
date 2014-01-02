package sb.quantocusta.dao;

import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;

import sb.quantocusta.api.Review;

import com.mongodb.DB;

/**
 * 
 * @author Giuliano Griffante
 */
public class ReviewDao extends Dao<Review> {

	public ReviewDao(DB db) {
		super(JacksonDBCollection.wrap(db.getCollection("reviews"), Review.class, String.class));
	}

	public Review findBy3rdId(String id) {
		return null;
	}
	
	public Review find(String idVenue, String idUser) {
		return coll.findOne(DBQuery.is("idVenue", idVenue).is("idUser", idUser));
	}

}
