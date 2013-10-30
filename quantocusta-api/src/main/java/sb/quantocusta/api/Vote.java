package sb.quantocusta.api;

import java.io.Serializable;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class Vote implements Serializable {
	
	private User user;
	private Venue venue;
	private String kind;
//	private Integer counter;
	
	public Vote() {
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Venue getVenue() {
		return venue;
	}
	
	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	
	public String getKind() {
		return kind;
	}
	
	public void setKind(String kind) {
		this.kind = kind;
	}
	
//	public Integer getCounter() {
//		return counter;
//	}
//	
//	public void setCounter(Integer counter) {
//		this.counter = counter;
//	}

}
