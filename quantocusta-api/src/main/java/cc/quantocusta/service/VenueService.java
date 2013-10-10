package cc.quantocusta.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/{city}")
@Produces(MediaType.APPLICATION_JSON)
public class VenueService {
	
	@GET
	public void home() {
		
	}
	
	@GET
	@Path("/{id}")
	public void get() {
		
	}
	
	@POST
	public void create() {
		
	}
	
	@PUT
	public void update() {
		
	}
	

}
