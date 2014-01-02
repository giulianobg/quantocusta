package sb.quantocusta.api;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class User {
	
	@ObjectId
	@JsonProperty("_id")
	private String id;
	private String thirdyId;
	private String name;
	private String email;
	
	public User() {
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getThirdyId() {
		return thirdyId;
	}
	
	public void setThirdyId(String thirdyId) {
		this.thirdyId = thirdyId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

}
