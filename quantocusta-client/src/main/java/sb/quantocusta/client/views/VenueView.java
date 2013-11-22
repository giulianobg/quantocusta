package sb.quantocusta.client.views;

import sb.quantocusta.api.Venue;

import com.yammer.dropwizard.views.View;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class VenueView extends View {
	
	private Venue venue;
	
	public VenueView(Venue venue) {
		super("/assets/tpl/venue.ftl");
		
		System.out.println(getCharset());
		
		this.venue = venue;
	}
	
	public Venue getVenue() {
		return venue;
	}
	
	public void setVenue(Venue venue) {
		this.venue = venue;
	}

}
