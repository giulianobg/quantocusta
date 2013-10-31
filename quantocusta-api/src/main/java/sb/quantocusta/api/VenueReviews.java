package sb.quantocusta.api;

import java.util.ArrayList;
import java.util.List;

public class VenueReviews {
	
	private Double averagePrice;
	private Integer usersCount;
	private List<Review> reviews;
	
	public VenueReviews() {
		reviews = new ArrayList<Review>();
		usersCount = 0;
		averagePrice = 0.0;
	}
	
	public Double getAveragePrice() {
		return averagePrice;
	}
	
	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}
	
	public List<Review> getReviews() {
		return reviews;
	}
	
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	public Integer getUsersCount() {
		return usersCount;
	}
	
	public void setUsersCount(Integer usersCount) {
		this.usersCount = usersCount;
	}

}
