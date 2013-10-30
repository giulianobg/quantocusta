package sb.quantocusta.dao;

import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;

import sb.quantocusta.api.Category;

import com.mongodb.DB;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class CategoryDao extends Dao<Category> {

	public CategoryDao(DB db) {
		super(JacksonDBCollection.wrap(db.getCollection("categories"), Category.class, String.class));
	}
	
	public Category findBy3rdId(String id) {
		return coll.findOne(DBQuery.is("id_foursquare", id));
	}

}
