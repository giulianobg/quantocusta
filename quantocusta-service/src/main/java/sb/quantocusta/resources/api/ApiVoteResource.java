package sb.quantocusta.resources.api;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response.Status;

import sb.quantocusta.api.Response;
import sb.quantocusta.api.Valuation;
import sb.quantocusta.api.Venue;
import sb.quantocusta.api.Vote;
import sb.quantocusta.dao.Daos;
import sb.quantocusta.dao.VenueDao;

import com.google.common.base.Optional;
import com.mongodb.DB;
import com.yammer.dropwizard.jersey.params.IntParam;

/**
 * 
 * @author Giuliano Griffante
 */
@Path("/api/vote")
@Produces("application/json; charset=utf-8")
public class ApiVoteResource {
	
//	private DB db;

	public ApiVoteResource(DB db) {
	}
	
	@POST
	public Response vote(@FormParam("id") String id,
			@FormParam("kind") String kind,
			@FormParam("v") IntParam v) {
		
		// usuário já votou nesse mesmo local?
		// Sim, update Vote e Venue
		// Não, insert Vote e update Venue
		
		
		Response r = new Response();
		try {
			VenueDao dao = Daos.get(VenueDao.class);
			
			Venue venue = dao.findById(id);
			 
			Valuation valuation = venue.getValuation().get(kind);
			if (valuation == null) {
				valuation = new Valuation();
			}
			
			if (v.get() > 0) { // Smile
				Optional<Integer> value = Optional.fromNullable(valuation.getSmileCount());
				valuation.setSmileCount(value.or(0) + 1);
			} else { // Pout
				Optional<Integer> value = Optional.fromNullable(valuation.getPoutCount());
				valuation.setPoutCount(value.or(0) + 1);
			}
			valuation.setTotalCount(Optional.fromNullable(valuation.getTotalCount()).or(0) + 1);
			
			venue.getValuation().put(kind, valuation);
			
			
			
			/* User ... */
			Vote vote = new Vote();
			vote.setKind(kind);
//			vote.setUser(user);
			vote.setVenue(venue);
			
//			Daos.get(VoteDao.class).insert(vote);
			
			
			
			
			dao.update(venue);
			
	//		Vote vote = new Vote();
	//		vote.setKind(kind);
	//		vote.
			
	//		VoteDao dao = Daos.get(VoteDao.class);
			
			r.setStatus(200);
			r.setResult(venue);
		} catch (Exception ex) {
			r.setStatus(500);
		}
		
		return r;
	}

	@GET
	@Path("price")
	public Object submitPrice(@QueryParam("id") String id,
			@QueryParam("user") String user,
			@QueryParam("price") Double price) {
		Response r = new Response();

		VenueDao dao = Daos.get(VenueDao.class);
		Venue venue = dao.findById(id);
		
//		venue.setAveragePrice(averagePrice);
		
		
		return javax.ws.rs.core.Response.status(Status.NO_CONTENT).build();
	}

}
