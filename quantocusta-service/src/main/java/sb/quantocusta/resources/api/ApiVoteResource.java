package sb.quantocusta.resources.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import sb.quantocusta.api.Response;
import sb.quantocusta.api.Valuation;
import sb.quantocusta.api.Venue;
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
@Consumes("application/json; charset=utf-8")
public class ApiVoteResource {
	
//	private DB db;

	public ApiVoteResource(DB db) {
	}
	
	@POST
	public Response vote(@QueryParam("id") String id,
			@QueryParam("kind") String kind,
			@QueryParam("v") IntParam v) {
		Response r = new Response();
		try {
			VenueDao dao = Daos.get(VenueDao.class);
			
			Venue venue = dao.findById(id);
			
			System.out.println(venue);
			 
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

	@POST
	@Path("price")
	public Response submitPrice(@QueryParam("id") String id,
			@QueryParam("type") String type,
			@QueryParam("v") String v) {
		return null;
	}

}
