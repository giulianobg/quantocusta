package sb.quantocusta.client.views;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import sb.quantocusta.api.Venue;

import com.yammer.dropwizard.views.View;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class SearchView extends View {
	
	private List<Venue> venues;
	
	public SearchView() {
		this(new ArrayList<Venue>());
	}
	
	public SearchView(List<Venue> venues) {
		super("/assets/tpl/search.ftl", Charset.forName("UTF-8"));
		
		this.venues = venues;
		
//		System.out.println(get);
		
		System.out.println("HomeView.enclosing_method()");
		
		System.out.println(getCharset());
		System.out.println();
	}
	
	public List<Venue> getVenues() {
		return venues;
	}
	
	public void setVenues(List<Venue> venues) {
		this.venues = venues;
	}

}
