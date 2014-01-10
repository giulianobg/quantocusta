package sb.quantocusta.resources;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sb.quantocusta.api.DataResponse;
import sb.quantocusta.api.Review;
import sb.quantocusta.api.User;
import sb.quantocusta.api.Valuation;
import sb.quantocusta.api.Venue;
import sb.quantocusta.api.Vote;
import sb.quantocusta.dao.Daos;
import sb.quantocusta.dao.ReviewDao;
import sb.quantocusta.dao.VenueDao;
import sb.quantocusta.dao.VoteDao;

import com.mongodb.BasicDBObject;
import com.yammer.dropwizard.auth.Auth;
import com.yammer.dropwizard.jersey.params.IntParam;

/**
 * 
 * @author Giuliano Griffante
 */
@Path("/api/vote")
@Produces("application/json; charset=utf-8")
public class VoteResource extends BaseResouce {

	static Logger LOG = LoggerFactory.getLogger(VoteResource.class);

	public VoteResource() {
	}

	@POST
	public Response vote(@Auth User user,
			@FormParam("id") String id,
			@FormParam("kind") String kind,
			@FormParam("v") IntParam v) {

		VenueDao dao = Daos.get(VenueDao.class);

		Venue venue = dao.findById(id);
		
		boolean iVeAlreadyVoted = Daos.get(VoteDao.class).find(id, user.getId()) != null;
		
		int value = v.get(); 
		if (iVeAlreadyVoted && v.get() < 0) {
		} else if (v.get() > 0) {
		} else {
			value = 0;
		}
		
		BasicDBObject valuations = venue.getValuation();
		LinkedHashMap<String, Object> valuation = null;
		if (kind.equals(Venue.FOOD)) {
			valuation = (LinkedHashMap<String, Object>) valuations.get(Venue.FOOD);
		} else if (kind.equals(Venue.ENVIRONMENT)) {
			valuation = (LinkedHashMap<String, Object>) valuations.get(Venue.ENVIRONMENT);
		} else if (kind.equals(Venue.TREATMENT)) {
			valuation = (LinkedHashMap<String, Object>) valuations.get(Venue.TREATMENT);
		}
			
		if (v.get() > 0) {
			if (!iVeAlreadyVoted) {
				valuations.put("totalCount", (Integer) valuations.get("totalCount") + 1);
			}
		}
		
		valuation.put("count", (Integer) valuation.get("count") + value);
		valuation.put("average", ((Integer) valuation.get("count") / new Double((Integer) valuations.get("totalCount"))) * 100);
		
		venue.setValuation(valuations);
			
//			if (StringUtils.equals(k, kind)) {
//				System.out.println("Calculating 'the' vote!");
//				System.out.println("Valued " + v.get());
				
//				if (v.get() > 0) {
//					Optional<Integer> value = Optional.fromNullable(valuation.getSmileCount());
//					valuation.setSmileCount(value.or(0) + 1);
//				} else {
//					Optional<Integer> value = Optional.fromNullable(valuation.getSmileCount());
//					valuation.setSmileCount(value.or(0) - 1);
//				}
//			}
			
//			if (!iVeAlreadyVoted) {
//				valuation.setTotalCount(Optional.fromNullable(valuation.getTotalCount()).or(0) + 1);
//			}
			
		
		/* Atualiza voto do usuário */
		// usuário já votou nesse mesmo local?
		// Sim, update Vote e Venue
		// Não, insert Vote e update Venue
		Vote vote = Daos.get(VoteDao.class).find(id, user.getId(), kind);
		if (vote == null) {
			vote = new Vote();
			vote.setIdVenue(id);
			vote.setIdUser(user.getId());
			vote.setKind(kind);
			vote.setCreatedAt(new Date());
			vote.setVal(v.get());
		}
		
		if (v.get() > 0) {
			// insert/update vote
			Daos.get(VoteDao.class).update(vote);
		} else {
			Daos.get(VoteDao.class).removeById(vote.getId());
		}

		// update venue
		venue = dao.update(venue);
		
		// incorpora valoração "me" aos dados do venue
		for (String k : Venue.VALUATION_KINDS) {
			LinkedHashMap<String, Object> aValuation = (LinkedHashMap<String, Object>) (venue.getValuation().get(k));
			Vote aVote = Daos.get(VoteDao.class).find(id, user.getId(), k);
			aValuation.put("me", new BasicDBObject("val", aVote == null ? 0 : aVote.getVal()));
		}

		return Response.ok(DataResponse.build(venue)).build();
	}

	@POST
	@Path("price")
	public Response submitPrice(@Auth User user,
			@FormParam("id") String id,
			@FormParam("price") Double price) {

//		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {

			VenueDao dao = Daos.get(VenueDao.class);
			Venue venue = dao.findById(id);

			Review review = null;

			if (venue == null) {
				Response.status(Status.NOT_FOUND).entity(DataResponse.build(Status.NOT_FOUND.getStatusCode())).build();
			} else {

				// verifica se usuário já avaliou esse local
				review = Daos.get(ReviewDao.class).find(id, user.getId());
				if (review != null) { 
					// update valoração desse usuário
					review.setPrice(price);

					Daos.get(ReviewDao.class).update(review);
				} else {
					// insere valoração desse usuário
					review = new Review();
					review.setIdUser(user.getId());
					review.setIdVenue(id);
					review.setPrice(price);
					review.setCreatedAt(new Date());

					Daos.get(ReviewDao.class).insert(review);
				}

				// salvar review
				//			venue.getReviews().getReviews().add(review);

				double nemAvPrice = venue.getReviews().getAveragePrice() * venue.getReviews().getUsersCount() + price;
				nemAvPrice = nemAvPrice / (venue.getReviews().getUsersCount() + 1);

				venue.getReviews().setAveragePrice(nemAvPrice);
				venue.getReviews().setUsersCount(venue.getReviews().getUsersCount() + 1);
			}

			venue = dao.update(venue);
			venue.getReviews().setMe(review);

			return Response.status(Status.OK).entity(DataResponse.build(Status.OK.getStatusCode(), venue)).build();
		}

		return Response.status(Status.FORBIDDEN).entity(DataResponse.build(Status.FORBIDDEN.getStatusCode())).build();
	}

}
