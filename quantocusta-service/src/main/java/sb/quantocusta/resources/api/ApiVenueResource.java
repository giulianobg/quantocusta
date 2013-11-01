package sb.quantocusta.resources.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.mongojack.JacksonDBCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sb.quantocusta.api.Category;
import sb.quantocusta.api.City;
import sb.quantocusta.api.DataResponse;
import sb.quantocusta.api.Review;
import sb.quantocusta.api.User;
import sb.quantocusta.api.Venue;
import sb.quantocusta.dao.CategoryDao;
import sb.quantocusta.dao.CityDao;
import sb.quantocusta.dao.Daos;
import sb.quantocusta.dao.ReviewDao;
import sb.quantocusta.dao.VenueDao;
import sb.quantocusta.resources.thirdy.FoursquareApi;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Optional;
import com.mongodb.DB;

/**
 * 
 * @author Giuliano Griffante
 *
 */
@Path("/api/venue")
@Produces("application/json; charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON)
public class ApiVenueResource {
	
	static Logger LOG = LoggerFactory.getLogger(ApiVenueResource.class);
	
//	private DB db;
	private JacksonDBCollection<Venue, String> coll;

	public ApiVenueResource(DB db) {
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
	public Response findById(@PathParam("id") String id, @Context HttpServletRequest request) {
		Venue venue = Daos.get(VenueDao.class).findById(id);
		
		if (venue == null) {
			return Response.status(Status.NOT_FOUND).entity(DataResponse.build(Status.NOT_FOUND.getStatusCode())).build();
		} else {
		
			User user = (User) request.getSession().getAttribute("user");
			if (user != null) {
				Review r = Daos.get(ReviewDao.class).find(id, user.getId());
				if (r != null) {
					venue.getReviews().setMe(r);
				}
			}
	
			return Response.status(Status.OK).entity(DataResponse.build(Status.OK.getStatusCode(), venue)).build();
		}
		
	}
	
	@GET
	@Path("thrd/{id}")
	public Object findBy3rdId(@PathParam("id") String id, @QueryParam("fat") Optional<String> fat) {
		Venue venue = Daos.get(VenueDao.class).findBy3rdId(id);
		
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
								
								dao.insert(category);
							}
							
							venue.setCategory(category);
						}
					}
				}

				venue = Daos.get(VenueDao.class).insert(venue);
				
				
//				if (!fat.or("false").equals("true")) {
//					venue.getReviews().getReviews().clear();
//				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			LOG.debug("Skipped Foursquare's retriving data from venue " + id);
		}
		
		
		
//		return Response.temporaryRedirect(UriBuilder.fromResource(ApiCategoryResource.class).path("{id}"))findById(venue.getId(), request);
		return venue;
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