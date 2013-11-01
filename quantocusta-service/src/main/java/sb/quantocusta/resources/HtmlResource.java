package sb.quantocusta.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import sb.quantocusta.api.Venue;
import sb.quantocusta.resources.api.ApiVenueResource;
import sb.quantocusta.resources.api.Apis;
import sb.quantocusta.views.SearchView;
import sb.quantocusta.views.SimplePageView;
import sb.quantocusta.views.VenueView;

import com.yammer.dropwizard.views.View;

@Path("/")
//@Produces("text/html; charset=utf-8")
@Produces("text/html; charset=iso-8859-1")
public class HtmlResource {
	
	@GET
	public SimplePageView home() {
		return new SimplePageView("/assets/tpl/index.ftl");
	}

	@GET
	@Path("buscar")
	public View search(@QueryParam("q") String q) {
		
//		URI uri = UriBuilder.fromResource(ApiVenueResource.class).path("search").queryParam("q", q).build();
//		System.out.println(Response.created(uri).toString());
//		Response r1 = Response.created(uri).entity(List.class).build();
		
//		Response r = r1.ok().build();
		
//		Response r = Response.created(uri).build();
//		List<Venue> venues = (List<Venue>) Response.created(uri).build().ok();
		
//		System.out.println(venues);
		
//		List<Venue> venues = null;
		
//		ObjectMapper mapper = new ObjectMapper();
		
//		TypeFactory.defaultInstance().constructCollectionType(List.class, Venue.class);
//		try {
//			venues = mapper.readValue(new URL(new URL("http://localhost:8080/"), "/api/venue/search?q=" + q), 
//					TypeFactory.defaultInstance().constructCollectionType(List.class, Venue.class));
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		ApiVenueResource resource = Apis.get(ApiVenueResource.class, "venue");
		List<Venue> venues = (List<Venue>) resource.search(q);

		return new SearchView(venues);
	}
	
	@GET
	@Path("thrd/{id}")
	public View get(@PathParam("id") String id) {
		ApiVenueResource resource = Apis.get(ApiVenueResource.class, "venue");
		
		Venue venue = (Venue) resource.findBy3rdId(id, null);
		
//		Response.status(301).build()
		
		return new VenueView(venue);
	}
	
}
