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
public class Vote implements Serializable {
	
	@ObjectId
	@JsonProperty("_id")
	private String id;
	private String idUser;
	private String idVenue;
	private String kind;
	private Integer val;
	private Date createdAt;
	
	public Vote() {
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
	
	public String getKind() {
		return kind;
	}
	
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public Integer getVal() {
		return val;
	}
	
	public void setVal(Integer val) {
		this.val = val;
	}
	
//	public Integer getCounter() {
//		return counter;
//	}
//	
//	public void setCounter(Integer counter) {
//		this.counter = counter;
//	}

}
