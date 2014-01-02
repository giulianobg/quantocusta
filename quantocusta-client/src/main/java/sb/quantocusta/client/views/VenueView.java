package sb.quantocusta.client.views;

import sb.quantocusta.api.Venue;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class VenueView extends SimplePageView {
	
	private Venue venue;
	
	public VenueView(Venue venue) {
		super("/assets/tpl/venue.ftl");
		this.venue = venue;
	}
	
	public Venue getVenue() {
		return venue;
	}
	
	public void setVenue(Venue venue) {
		this.venue = venue;
	}

}
