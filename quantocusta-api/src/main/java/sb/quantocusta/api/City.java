package sb.quantocusta.api;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.PrePersist;

import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the city database table.
 */
@MongoCollection(name="cities")
public class City implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ObjectId
	@JsonProperty("_id")
	private String id;

//	@Valid
	private Country country;
	private String name;
	private String state;
	private String context;
	private String status;
	private Date createdAt;
	private Date updatedAt;
	
	public City() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Country getCountry() {
		return country;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getContext() {
		return context;
	}
	
	public void setContext(String context) {
		this.context = context;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@PrePersist
	protected void onCreate() {
		System.out.println("City.onCreate()");
//		setCreatedAt(new Date());
//		setUpdatedAt(getCreatedAt());
	}
//
//	@PreUpdate
//	protected void onUpdate() {
//		setUpdatedAt(new Date());
//	}

}