package sb.quantocusta.api;

import java.io.Serializable;
import java.util.Date;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class Country implements Serializable {

	@ObjectId
	@JsonProperty("_id")
	private String id;
	
	private String code;

	private String name;
	
	private Date createdAt;

	private Date updatedAt;

	public Country() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

//	@PrePersist
//	protected void onCreate() {
//		setCreatedAt(new Date());
//		setUpdatedAt(getCreatedAt());
//	}
//
//	@PreUpdate
//	protected void onUpdate() {
//		setUpdatedAt(new Date());
//	}
//
}