package sb.quantocusta.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mongodb.DB;
import com.yammer.metrics.annotation.Timed;

@Path("/venue")
@Produces(MediaType.APPLICATION_JSON)
public class VenueResource {
	
	private DB db; 

	public VenueResource(DB db) {
		this.db = db;
	}
	
	@GET
	public Object index() {
//		return jetty
		return "test";
	}
	
	@Timed
	@Path("search")
	public Object search() {
		return "searchhhh";
	}
	
	@POST
	@Path("create")
	@Consumes
	public Object create() {
		return "c";
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
