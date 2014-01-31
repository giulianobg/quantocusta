package sb.quantocusta.client.views;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private Map<String, Object> params;
	
	public SimplePageView(String path) {
		this(path, null);
	}
	
	public SimplePageView(String path, User me) {
		super(path, Charset.forName("UTF-8"));
		this.me = me;
		this.params = new HashMap<String, Object>();
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
	
	public Map<String, Object> getParams() {
		return params;
	}
	
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
	public void addParam(String key, Object value) {
		params.put(key, value);
	}
	
}
