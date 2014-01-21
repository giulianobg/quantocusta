package sb.quantocusta.resources;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.mongojack.DBCursor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sb.quantocusta.api.Category;
import sb.quantocusta.api.CategoryRef;
import sb.quantocusta.api.City;
import sb.quantocusta.api.Comment;
import sb.quantocusta.api.DataResponse;
import sb.quantocusta.api.Review;
import sb.quantocusta.api.User;
import sb.quantocusta.api.Venue;
import sb.quantocusta.api.Vote;
import sb.quantocusta.dao.CategoryDao;
import sb.quantocusta.dao.CityDao;
import sb.quantocusta.dao.CommentDao;
import sb.quantocusta.dao.Daos;
import sb.quantocusta.dao.ReviewDao;
import sb.quantocusta.dao.SessionDao;
import sb.quantocusta.dao.UserDao;
import sb.quantocusta.dao.VenueDao;
import sb.quantocusta.dao.VoteDao;
import sb.quantocusta.resources.thirdy.FoursquareApi;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Optional;
import com.mongodb.BasicDBObject;
import com.yammer.dropwizard.auth.Auth;

/**
 * 
 * @author Giuliano Griffante
 *
 */
@Path("/api/venue")
@Produces("application/json; charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON)
public class VenueResource extends BaseResouce {
	
	static Logger LOG = LoggerFactory.getLogger(VenueResource.class);
	
	public VenueResource() {
	}
	
	@GET
	@Path("search")
	public Response search(@QueryParam("q") String q, @QueryParam("lat") String lat, @QueryParam("lng") String lng) {
		VenueDao dao = Daos.get(VenueDao.class);
		
		if (StringUtils.isEmpty(lat) || StringUtils.isEmpty(lng)) {
			return Response.status(Status.BAD_REQUEST).entity(DataResponse.build(Status.BAD_REQUEST)).build();
		}
		
		List<Venue> sqVenues = FoursquareApi.search(lat, lng, q);
		
		List<Venue> venues = new ArrayList<Venue>();
		for (Venue v : sqVenues) {
			Venue venue = dao.findBy3rdId(v.getIdFoursquare());
			if (venue != null) {
				venues.add(venue);
			} else {
				venues.add(v);
			}
		}
		
		return Response.ok(DataResponse.build(venues)).build();
	}
	
	@GET
	@Path("near")
	public Response near(@Auth User user, @QueryParam("lat") String lat, @QueryParam("lng") String lng) {
		VenueDao dao = Daos.get(VenueDao.class);
		
		if (StringUtils.isEmpty(lat) || StringUtils.isEmpty(lng)) {
			return Response.status(Status.BAD_REQUEST).entity(DataResponse.build(Status.BAD_REQUEST)).build();
		}
		
		List<Venue> sqVenues = FoursquareApi.search(lat, lng, "");
		
		List<Venue> venues = new ArrayList<Venue>();
		for (Venue v : sqVenues) {
			if (v.getId() != null) {
				venues.add(v);
			} else {
				Venue venue = dao.findBy3rdId(v.getIdFoursquare());
				if (venue != null) {
					venues.add(venue);
				} else {
					venues.add(v);
				}
			}
		}
		
		return Response.ok(DataResponse.build(venues)).build();
	}
	
	@GET
	@Path("{id}")
	public Response findById(@PathParam("id") String id, @QueryParam("access_token") String token) {
		UserDao uDao = Daos.get(UserDao.class);

		Venue venue = Daos.get(VenueDao.class).findById(id);
		
		if (venue == null) {
			return Response.status(Status.NOT_FOUND).entity(DataResponse.build(Status.NOT_FOUND)).build();
		} else {
			User user = null;
			if (token != null) {
				user = uDao.findById(Daos.get(SessionDao.class).find(token).getUserId());
			}
			
			if (venue.getCategory() != null) {
				venue.setCategory(Daos.get(CategoryDao.class).findById(venue.getCategory().getId()));
			}
			
			if (user != null) {
				Review r = Daos.get(ReviewDao.class).find(id, user.getId());
				if (r != null) {
					venue.getReviews().setMe(r);
				}
				
				// Load valuations
				for (String kind : Venue.VALUATION_KINDS) {
					LinkedHashMap<String, Object> valuation = (LinkedHashMap<String, Object>) (venue.getValuation().get(kind));
					Vote vote = Daos.get(VoteDao.class).find(id, user.getId(), kind);
					valuation.put("me", new BasicDBObject("val", vote == null ? 0 : vote.getVal()));
				}
			}
			
			// load comments
			BasicDBObject query = new BasicDBObject("access_token", token);
			DBCursor<Comment> commentsCursor = Daos.get(CommentDao.class).findAll(query);
			List<Comment> comments = new ArrayList<Comment>();
			while (commentsCursor.hasNext()) {
				Comment comment = commentsCursor.next();
				comment.setUserInstance(Daos.get(UserDao.class).findById(comment.getUser().getId()));
				comment.setUser(null);
				comments.add(comment);
			}
			venue.setComments(comments);
	
			return Response.ok(DataResponse.build(Status.OK.getStatusCode(), venue)).build();
		}
		
	}
	
//	@GET
//	@Path("{id}/comments")
//	public Response comments() {
//		
//	}
	
	@GET
	@Path("thrd/{id}")
	public Response findBy3rdId(@PathParam("id") String id, @QueryParam("fat") Optional<String> fat, @QueryParam("access_token") String token) {
		Venue venue = Daos.get(VenueDao.class).findBy3rdId(id);
		
//		User user = null;
//		if (token != null) {
//			
//		}
		
		if (venue == null) {
			try {
				JsonNode node = FoursquareApi.get(id);
				
				if (node != null) {
					venue = new Venue();
					venue.setIdFoursquare(node.get("id").asText());
					venue.setName(node.get("name").asText());
					if (node.get("location") != null && 
							node.get("location").get("address") != null) {
						venue.setAddress(node.get("location").get("address").asText());
						venue.setLat(node.get("location").get("lat").asDouble());
						venue.setLng(node.get("location").get("lng").asDouble());
						
						if (node.get("contact") != null &&
								node.get("contact").get("formattedPhone") != null) {
							venue.setPhone(node.get("contact").get("formattedPhone").asText());
						}
						
						if (node.get("location").get("city") != null) {
							CityDao dao = Daos.get(CityDao.class);
							City city = dao.findBy3rdId(node.get("location").get("city").asText());
							if (city == null) {
								city = new City();
								city.setName(node.get("location").get("city").asText());
								city.setState(node.get("location").get("state").asText());
								city = dao.insert(city);
							}
//							venue.setCity(new DBRef<City, String>(city.getId(), City.class));
							venue.setCity(city);
						}
						
						if (node.get("location").get("country") != null) {
							
						}
						
						if (node.get("categories") != null && node.get("categories").size() > 0) {
							CategoryDao dao = Daos.get(CategoryDao.class);
							Category category = dao.findBy3rdId(node.get("categories").get(0).get("id").asText());
							
							if (category == null) {
								category = new Category();
								category.setIdFoursquare(node.get("categories").get(0).get("id").asText());
								category.setName(node.get("categories").get(0).get("name").asText());
								
								category = dao.insert(category);
							}
							CategoryRef ref = new CategoryRef();
							ref.setId(category.getId());
							venue.setCategory(ref);
						}
					}
				}

				venue = Daos.get(VenueDao.class).insert(venue);
				
				if (venue.getCategory() != null) {
					venue.setCategory(Daos.get(CategoryDao.class).findById(venue.getCategory().getId()));
				}
				
//				if (!fat.or("false").equals("true")) {
//					venue.getReviews().getReviews().clear();
//				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			LOG.debug("Skipped Foursquare's retriving data from venue " + id);
		}
		
		if (venue != null) {
			return findById(venue.getId(), token);
		}
		
		return Response.status(Status.NOT_FOUND).entity(DataResponse.build(Status.NOT_FOUND)).build();
	}
	
	@POST
	@Path("create")
	public Object create(@QueryParam("id") String id) {
//		Venue v = (Venue) get(id);
//		
////		System.out.println(UUID.genRandom32Hex());
////		
////		v.setId(UUID.genRandom32Hex());
//		
//		
//		WriteResult<Venue, String> result = coll.insert(v);
//		System.out.println(result);
//		result.get
//		String id = result.getSavedId();
//		Venue savedObject = coll.findOneById(id);
//		System.out.println(savedObject);
		
		
		
		return null;
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
