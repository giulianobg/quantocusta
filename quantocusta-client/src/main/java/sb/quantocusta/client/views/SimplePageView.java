package sb.quantocusta.client.views;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import sb.quantocusta.api.User;
import sb.quantocusta.api.Venue;

import com.yammer.dropwizard.views.View;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class SimplePageView extends View {
	
	private User me;
	private List<Venue> venues;
	private Venue venue;
	private HttpServletRequest request;
	
	public SimplePageView(String path) {
		this(path, null);
	}
	
	public SimplePageView(String path, User me) {
		super(path, Charset.forName("UTF-8"));
		this.me = me;
	}
	
	public User getMe() {
		return me;
	}
	
	public void setMe(User me) {
		this.me = me;
	}
	
	public Venue getVenue() {
		return venue;
	}
	
	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	
	public List<Venue> getVenues() {
		return venues;
	}
	
	public void setVenues(List<Venue> venues) {
		this.venues = venues;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
