package sb.quantocusta.dao;

import org.mongojack.JacksonDBCollection;

import sb.quantocusta.api.Comment;

import com.mongodb.DB;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class CommentDao extends Dao<Comment> {
	
	public CommentDao(DB db) {
		super(JacksonDBCollection.wrap(db.getCollection("comments"), Comment.class, String.class));
	}
	
}
