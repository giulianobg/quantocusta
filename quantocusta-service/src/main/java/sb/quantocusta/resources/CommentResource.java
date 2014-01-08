package sb.quantocusta.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.mongojack.DBRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sb.quantocusta.api.Comment;
import sb.quantocusta.api.DataResponse;
import sb.quantocusta.api.User;
import sb.quantocusta.api.Venue;
import sb.quantocusta.dao.CommentDao;
import sb.quantocusta.dao.Daos;
import sb.quantocusta.dao.VenueDao;

import com.yammer.dropwizard.auth.Auth;

/**
 * 
 * @author Giuliano Griffante
 */
@Path("/api/comment")
@Produces("application/json; charset=utf-8")
public class CommentResource extends BaseResouce {

	static Logger LOG = LoggerFactory.getLogger(CommentResource.class);

	public CommentResource() {
	}

	@POST
	public Response comment(@Auth User user,
			@FormParam("id") String id,
			@FormParam("comment") String comment) {

		VenueDao dao = Daos.get(VenueDao.class);

		Comment instance = new Comment();
		instance.setComment(comment);
		instance.setUser(new DBRef<User, String>(user.getId(), User.class));
		instance.setVenue(new DBRef<Venue, String>(dao.findById(id).getId(), Venue.class));
		
		// insert/update vote
		instance = Daos.get(CommentDao.class).insert(instance);

		// update venue
//		venue = dao.update(venue);

		return Response.ok(DataResponse.build(instance)).build();
	}

}
