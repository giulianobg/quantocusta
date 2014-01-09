package sb.quantocusta.api;

import java.io.Serializable;
import java.util.Date;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class CategoryRef extends Category implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ObjectId
	@JsonProperty("_id")
	private String id;
	
	@JsonIgnore
	@JsonProperty("id_foursquare")
	private String idFoursquare;

	@JsonIgnore
	private String name;
	
	@JsonIgnore
	private Category category;
	
	@JsonIgnore
	private Date createdAt;
	
	@JsonIgnore
	private Date updatedAt;

	public CategoryRef() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getIdFoursquare() {
		return idFoursquare;
	}
	
	public void setIdFoursquare(String idFoursquare) {
		this.idFoursquare = idFoursquare;
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

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

//	public List<Category> getCategories() {
//		return this.categories;
//	}
//
//	public void setCategories(List<Category> categories) {
//		this.categories = categories;
//	}

}