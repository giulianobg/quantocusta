package sb.quantocusta.resources;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import sb.quantocusta.api.Venue;
import sb.quantocusta.views.SearchView;
import sb.quantocusta.views.SimplePageView;
import sb.quantocusta.views.VenueView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.yammer.dropwizard.views.View;

@Path("/")
//@Produces("text/html; charset=utf-8")
@Produces(MediaType.TEXT_HTML)
public class HtmlResource {
	
	@GET
	public SimplePageView home() {
		return new SimplePageView("/assets/tpl/index.ftl");
	}

	@GET
	@Path("buscar")
	@Produces("text/html; charset=utf-8")
	public View search(@QueryParam("q") String q) {
		List<Venue> venues = null;
		
		ObjectMapper mapper = new ObjectMapper();
		
		TypeFactory.defaultInstance().constructCollectionType(List.class, Venue.class);
		try {
			venues = mapper.readValue(new URL(new URL("http://localhost:8080/"), "/api/venue/search?q=" + q), 
					TypeFactory.defaultInstance().constructCollectionType(List.class, Venue.class));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		List<Venue> venues = (List<Venue>) resource.search(q);

//		System.out.println(venues.get(0).getName());

		return new SearchView(venues);
	}
	
	@GET
	@Path("thrd/{id}")
	public View get(@PathParam("id") String id) {
		ApiVenueResource resource = Apis.get(ApiVenueResource.class, "venue");
		
		Venue venue = (Venue) resource.get(id);
		
		return new VenueView(venue);
	}
	
}
