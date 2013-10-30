package sb.quantocusta.api;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	
	@ObjectId
	@JsonProperty("_id")
	private String id;
	
	private String name;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
