package sb.quantocusta.api;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.Transient;

import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.json.JsonSnakeCase;

//@JsonSnakeCase
public class Venue implements Serializable {
	
	@ObjectId
	@JsonProperty("_id")
	private String id;
//	
//	@ManyToOne
//	@JoinColumn(name="id_category")
	private Category category;

	private String address;

	private City city;

	@JsonProperty("id_foursquare")
	private String idFoursquare;

	private String name;

	private String pic;

	private Integer state;

	private String status;
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name="created_at", updatable=false)
	private Date createdAt;
//	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name="updated_at")
	private Date updatedAt;
//
//	@OneToMany(mappedBy="venue", fetch=FetchType.EAGER)
//	private List<Experience> experiences;
//	
//	@ManyToMany(fetch=FetchType.LAZY, mappedBy="venues")
//	private List<model.List> list;
//	
//	//bi-directional many-to-many association to Person
//	@ManyToMany(fetch=FetchType.LAZY)
//	@JoinTable(
//		name="favorite",
//		joinColumns={
//			@JoinColumn(name="id_venue")
//			},
//		inverseJoinColumns={
//			@JoinColumn(name="id_person")
//			}
//		)
//	private List<Person> people;
//	
	/* transients attributes */
	@Transient
	private Double averagePrice;
//	
//	@Transient
//	private String topWhyYes;
//	
//	@Transient
//	private String topWhyNo;

	public Venue() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public City getCity() {
		return city;
	}
	
	public void setCity(City city) {
		this.city = city;
	}

	public String getIdFoursquare() {
		return this.idFoursquare;
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
	
	public String getPic() {
		return pic;
	}
	
	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
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

//	public List<Experience> getExperiences() {
//		return experiences;
//	}
//	
//	public void setExperiences(List<Experience> experiences) {
//		this.experiences = experiences;
//	}
	
//	public List<model.List> getList() {
//		return list;
//	}
//	
//	public void setList(List<model.List> list) {
//		this.list = list;
//	}
	
//	public List<Person> getPeople() {
//		return people;
//	}
//	
//	public void setPeople(List<Person> people) {
//		this.people = people;
//	}
	
	public Double getAveragePrice() {
		return averagePrice;
	}
	
	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}
	
//	public String getFormattedAveragePrice() {
//		DecimalFormatSymbols custom = new DecimalFormatSymbols(Locale.getDefault());
//		custom.setDecimalSeparator(',');
//		DecimalFormat df = new DecimalFormat("0.00##", custom);
//		return df.format(getAveragePrice());
//	}
	
	@PrePersist
	protected void onPersist() {
		setCreatedAt(new Date());
		setUpdatedAt(getCreatedAt());
	}
	

//	@PreUpdate 
//	protected void onUpdate() {
//		setUpdatedAt(new Date());
//	}
//	
//	@PostLoad
//	protected void postLoad() {
//		Double amount = 0.0;
//		Integer amountPeople = 0;
//		for (Experience exp : getExperiences()) {
//			if (exp.getHowMuch() != null && exp.getHowMuch() > 0) {
//				amount += exp.getHowMuch();
//				amountPeople += (exp.getHowManyPeople() != null && exp.getHowManyPeople() > 0) ? exp.getHowManyPeople() : 1;
//			}
//		}
//		
//		setAveragePrice(new Double(Math.round(amount / amountPeople)));
//	}

}