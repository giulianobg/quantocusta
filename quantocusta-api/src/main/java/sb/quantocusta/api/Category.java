package sb.quantocusta.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.mongojack.ObjectId;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class Category implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ObjectId
	private Long id;

	private String name;
	
	private Category category;

	private List<Category> categories;
	
	private Date createdAt;

	private Date updatedAt;

	public Category() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
//	
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

}