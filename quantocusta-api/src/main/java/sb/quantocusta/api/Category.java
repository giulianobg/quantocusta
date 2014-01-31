package sb.quantocusta.api;

import java.io.Serializable;
import java.util.Date;

import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Giuliano Griffante
 *
 */
@MongoCollection(name="categories")
public class Category implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ObjectId
	@JsonProperty("_id")
	private String id;
	
	@JsonProperty("id_foursquare")
	private String idFoursquare;

	private String name;
	
	@JsonInclude(Include.NON_NULL)
	private Category category;
	
	private Date createdAt;
	private Date updatedAt;

	public Category() {
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
	
	@Override
	public String toString() {
		if (getId() != null) {
			return "[" + getId() + ":" + getIdFoursquare() + "] " + getName();
		}

		return super.toString();
	}

}