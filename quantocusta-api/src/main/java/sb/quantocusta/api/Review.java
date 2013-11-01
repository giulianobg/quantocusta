package sb.quantocusta.api;

import java.util.Date;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Giuliano Griffante
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Review {
	
	@ObjectId
	@JsonProperty("_id")
	private String id;
	@JsonIgnore private String idUser;
	@JsonIgnore private String idVenue;
	private Double price;
	private Date createdAt;
	
	public Review() {
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getIdUser() {
		return idUser;
	}
	
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	
	public String getIdVenue() {
		return idVenue;
	}
	
	public void setIdVenue(String idVenue) {
		this.idVenue = idVenue;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
