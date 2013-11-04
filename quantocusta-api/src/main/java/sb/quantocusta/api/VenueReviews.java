package sb.quantocusta.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author Giuliano Griffante
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VenueReviews {
	
	private Double averagePrice;
	private Integer usersCount;
	
	@JsonInclude(Include.NON_NULL)
	private Review me;
	
	public VenueReviews() {
//		reviews = new ArrayList<Review>();
		usersCount = 0;
		averagePrice = 0.0;
	}
	
	public Double getAveragePrice() {
		return averagePrice;
	}
	
	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}
	
	public Review getMe() {
		return me;
	}
	
	public void setMe(Review me) {
		this.me = me;
	}
	
//	public List<Review> getReviews() {
//		return reviews;
//	}
//	
//	public void setReviews(List<Review> reviews) {
//		this.reviews = reviews;
//	}
	
	public Integer getUsersCount() {
		return usersCount;
	}
	
	public void setUsersCount(Integer usersCount) {
		this.usersCount = usersCount;
	}

}
