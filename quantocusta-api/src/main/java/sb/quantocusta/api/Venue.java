package sb.quantocusta.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.mongojack.DBRef;
import org.mongojack.MongoCollection;
import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.BasicDBObject;

/**
 * 
 * @author Giuliano Griffante
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@MongoCollection(name="venues")
public class Venue implements Serializable {

	public static final String ENVIRONMENT = "environment";
	public static final String FOOD = "food";
	public static final String TREATMENT = "treatment";

	public static final String[] VALUATION_KINDS = {ENVIRONMENT, FOOD, TREATMENT};

	@ObjectId
	@JsonProperty("_id")
	private String id;

	//	@JsonDeserialize(contentConverter)
	private Category category;
	//	private Category category;

	private Double lat;
	private Double lng;
	private String address;

	//	private DBRef<City, String> city;
	private City city;

	private String phone;

	@JsonProperty("id_foursquare")
	private String idFoursquare;

	private String name;

	private String pic;

	private Integer state;

	private String status;

	private BasicDBObject valuation;

	private Date createdAt;
	private Date updatedAt;
	private VenueReviews reviews;

	@ObjectId
	private List<DBRef<Comment, String>> comments;

	public Venue() {
		valuation = new BasicDBObject();
		valuation.put("totalCount", new Integer(0));
		valuation.put(Venue.ENVIRONMENT, new Valuation());
		valuation.put(Venue.FOOD, new Valuation());
		valuation.put(Venue.TREATMENT, new Valuation());

		reviews = new VenueReviews();
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

	//	public void setCategory(Category category) {
	//		this.category = new DBRef<Category, String>(category.getId(), Category.class);
	//	}

	//	public Category getCategory() {
	//		return category;
	//	}
	//	
	//	public void setCategory(Category category) {
	//		this.category = category;
	//	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	//	public DBRef<City, String> getCity() {
	//		return city;
	//	}
	//	
	//	public void setCity(DBRef<City, String> city) {
	//		this.city = city;
	//	}

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	//	public Double getAveragePrice() {
	//		return averagePrice;
	//	}
	//	
	//	public void setAveragePrice(Double averagePrice) {
	//		this.averagePrice = averagePrice;
	//	}

	//	public Map<String, Valuation> getValuation() {
	//		return valuation;
	//	}
	//	
	//	public void setValuation(Map<String, Valuation> valuation) {
	//		this.valuation = valuation;
	//	}

	public BasicDBObject getValuation() {
		return valuation;
	}

	public void setValuation(BasicDBObject valuation) {
		this.valuation = valuation;
	}

	public VenueReviews getReviews() {
		return reviews;
	}

	public void setReviews(VenueReviews reviews) {
		this.reviews = reviews;
	}

	public List<DBRef<Comment, String>> getComments() {
		return comments;
	}

	public void setComments(List<DBRef<Comment, String>> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		if (getId() != null) {
			return getId();
		}

		return super.toString();
	}

}