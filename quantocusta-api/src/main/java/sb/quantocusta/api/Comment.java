package sb.quantocusta.api;

import java.util.Date;

import org.mongojack.DBRef;
import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author Giuliano Griffante
 *
 */
@MongoCollection(name="comments")
public class Comment {
	
	@ObjectId
	@JsonProperty("_id")
	private String id;
	
	@JsonInclude(Include.NON_NULL)
	private DBRef<User, String> user;
	private User userInstance;
	
	private DBRef<Venue, String> venue;
	
	private String comment;
	
	private Date createAt;
	
	public Comment() {
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public DBRef<User, String> getUser() {
		return user;
	}
	
	public void setUser(DBRef<User, String> user) {
		this.user = user;
	}
	
	public User getUserInstance() {
		return userInstance;
	}
	
	public void setUserInstance(User userInstance) {
		this.userInstance = userInstance;
	}
	
	@JsonIgnore
	public DBRef<Venue, String> getVenue() {
		return venue;
	}
	
	public void setVenue(DBRef<Venue, String> venue) {
		this.venue = venue;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Date getCreateAt() {
		return createAt;
	}
	
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

}
