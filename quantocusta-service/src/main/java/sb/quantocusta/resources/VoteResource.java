package sb.quantocusta.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.mongodb.DB;

/**
 * 
 * @author Giuliano Griffante
 */
@Path("/ppp")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VoteResource {
	
	private DB db; 

	public VoteResource(DB db) {
		this.db = db;
	}
	
	@POST
	@Path("")
	public Object submitValue(@QueryParam("id") String id,
			@QueryParam("type") String type,
			@QueryParam("v") String v) {
		return null;
	}

	@POST
	@Path("price")
	public Object submitPrice(@QueryParam("id") String id,
			@QueryParam("type") String type,
			@QueryParam("v") String v) {
//		db.
		return null;
	}

}
