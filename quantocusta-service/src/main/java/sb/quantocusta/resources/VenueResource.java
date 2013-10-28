package sb.quantocusta.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import sb.quantocusta.api.Venue;
import sb.quantocusta.resources.thirdy.FoursquareApi;

import com.mongodb.DB;
import com.yammer.dropwizard.jersey.params.LongParam;

@Path("/venue")
@Produces(MediaType.APPLICATION_JSON)
public class VenueResource {
	
	private DB db;
	private JacksonDBCollection<Venue, String> coll;

	public VenueResource(DB db) {
		this.db = db;
		coll = JacksonDBCollection.wrap(db.getCollection("test"), Venue.class, String.class);
	}
	
	@GET
	public Object index() {
//		db.getCollection("venues").
//		return jetty
		return "test";
	}

	@GET
	@Path("search")
	public Object search(@QueryParam("q") String q) {
		List<Venue> venues = FoursquareApi.search("Porto+Alegre", q);
		
		return venues;
	}
	
	@GET
	@Path("{id}")
	public Object get(@PathParam("id") LongParam userId) {
//		List<Venue> venues = FoursquareApi.search("Porto+Alegre", q);
//		db.getCollection("venues").getName()
		
		return "venueX";
	}
	
	@GET
	@Path("thrd/{id}")
	public Object get(@PathParam("id") String id) {
		Venue venue = null;
		try {
			venue = FoursquareApi.get(id);
			System.out.println(venue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return venue;
	}
	
	@POST
	@Path("create")
	public Object create() {
		Venue v = (Venue) get("4ce3e507a3c6721eadde12cf");
		
		WriteResult<Venue, String> result = coll.insert(v);
//		String id = result.getSavedId();
//		Venue savedObject = coll.findOneById(id);
//		System.out.println(savedObject);
		
		return v;
	}
	
	@PUT
	@Path("update")
	@Consumes
	public String update() {
		return "update";
	}
	
	@DELETE
	@Path("delete")
	public Object delete() {
		return "del";
	}

}
