package sb.quantocusta.resources;

import java.net.URI;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sb.quantocusta.api.DataResponse;
import sb.quantocusta.api.User;
import sb.quantocusta.dao.Daos;
import sb.quantocusta.dao.UserDao;

import com.sun.jersey.api.client.WebResource;
import com.yammer.dropwizard.auth.Auth;

/**
 * 
 * @author Giuliano Griffante
 *
 */
@Path("/api/user")
@Produces("application/json; charset=utf-8")
//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class UserResource extends BaseResouce {

	static Logger LOG = LoggerFactory.getLogger(UserResource.class);

	public UserResource() {
	}

	@GET
	@Path("me")
	public Response me(@Auth User user) {
		return Response.status(Status.OK).entity(DataResponse.build(Status.OK, user)).build();
	}

	@GET
	@Path("{id}")
	public Response findById(@Auth User user, @PathParam("id") String id) {
//		if (TokenUtils.tokenFromId(id).equals(token)) {
//			UserDao dao = Daos.get(UserDao.class);
//			User user = dao.findById(id);
			if (user != null) {
				URI uri = UriBuilder.fromResource(SessionResource.class).
						path("create").
						build();
				
//				WebResource target = client.resource(uri);
				
//				create(user.getId());

				return Response.status(Status.OK).entity(DataResponse.build(user)).build();
			}
//		} else {
//			return Response.status(Status.FORBIDDEN).entity(DataResponse.build(Status.FORBIDDEN)).build();
//		}

		return Response.status(Status.NOT_FOUND).entity(DataResponse.build(Status.NOT_FOUND)).build();
	}

	@POST
	@Path("create")
	public Response create(@FormParam("thirdyId") String thirdyId,
			@FormParam("name") String name,
			@FormParam("email") String email) {
		UserDao dao = Daos.get(UserDao.class);

		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setThirdyId(thirdyId);

		user = dao.insert(user);

		return Response.status(Status.OK).entity(DataResponse.build(user)).build();
	}

	//	@PUT
	//	@Path("update")
	//	@Consumes
	//	public String update() {
	//		return "update";
	//	}
	//	
	//	@DELETE
	//	@Path("delete")
	//	public Object delete() {
	//		return "del";
	//	}

}
