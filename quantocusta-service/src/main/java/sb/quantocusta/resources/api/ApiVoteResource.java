package sb.quantocusta.resources.api;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sb.quantocusta.api.Response;
import sb.quantocusta.api.Review;
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
	
	static Logger LOG = LoggerFactory.getLogger(ApiVoteResource.class);

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
			
			valuation.setPoutAverage((valuation.getPoutCount() / (double) valuation.getTotalCount()) * 100);
			valuation.setSmileAverage((valuation.getSmileCount() / (double) valuation.getTotalCount()) * 100);
			
			venue.getValuation().put(kind, valuation);
			
			/* User ... */
			Vote vote = new Vote();
			vote.setKind(kind);
//			vote.setUser(user);
			vote.setVenue(venue);
			
//			Daos.get(VoteDao.class).insert(vote);
			
			
			
			
			venue = dao.update(venue);
			
	//		Vote vote = new Vote();
	//		vote.setKind(kind);
	//		vote.
			
	//		VoteDao dao = Daos.get(VoteDao.class);
			
			r.setStatus(200);
			r.setResult(venue);
		} catch (Exception ex) {
			LOG.error(ex.getMessage(), ex);
			r.setStatus(500);
		}
		
		return r;
	}

	@POST
	@Path("price")
	public Object submitPrice(@FormParam("id") String id,
//			@@FormParam("user") String user,
			@FormParam("price") Double price) {
		VenueDao dao = Daos.get(VenueDao.class);
		Venue venue = dao.findById(id);
		
		if (venue == null) {
			return Response.build(404);
		} else {
			
			// insere valoração desse usuário
			// TODO ... // talvez não precise
			
			Review review = new Review();
			review.setCreatedAt(System.currentTimeMillis());
			review.setPrice(price);
//			review.setIdUser(idUser);
			
			venue.getReviews().getReviews().add(review);
			
			double nemAvPrice = venue.getReviews().getAveragePrice() * venue.getReviews().getUsersCount() + price;
			nemAvPrice = nemAvPrice / (venue.getReviews().getUsersCount() + 1);
			
			venue.getReviews().setAveragePrice(nemAvPrice);
			venue.getReviews().setUsersCount(venue.getReviews().getUsersCount() + 1);
		}
		
		venue = dao.update(venue);
		
		return Response.build(200, venue);
	}

}
