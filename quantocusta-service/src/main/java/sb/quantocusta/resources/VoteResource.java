package sb.quantocusta.resources;

import java.util.Date;

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

import com.google.common.base.Optional;
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
	public Response vote(@FormParam("id") String id,
			@FormParam("kind") String kind,
			@FormParam("v") IntParam v) {

		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
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
			}
			
			vote.setVal(v.get());

			// insert/update vote
			Daos.get(VoteDao.class).update(vote);

			// update venue
			venue = dao.update(venue);
			
			// incorpora valoração "me" aos dados do venue
			venue.getValuation().get(Venue.FOOD).setMe(Daos.get(VoteDao.class).find(id, user.getId(), Venue.FOOD));
			venue.getValuation().get(Venue.TREATMENT).setMe(Daos.get(VoteDao.class).find(id, user.getId(), Venue.TREATMENT));
			venue.getValuation().get(Venue.ENVIRONMENT).setMe(Daos.get(VoteDao.class).find(id, user.getId(), Venue.ENVIRONMENT));

			return Response.ok(DataResponse.build(venue)).build();
		}

		return Response.status(Status.FORBIDDEN).entity(DataResponse.build(Status.FORBIDDEN)).build();
	}

	@POST
	@Path("price")
	public Response submitPrice(@FormParam("id") String id,
			@FormParam("price") Double price) {

		User user = (User) request.getSession().getAttribute("user");
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
