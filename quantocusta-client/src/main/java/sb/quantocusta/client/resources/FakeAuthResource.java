package sb.quantocusta.client.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import sb.quantocusta.api.User;
import sb.quantocusta.dao.Daos;
import sb.quantocusta.dao.UserDao;
import sb.quantocusta.resources.BaseResouce;
import ch.qos.logback.core.status.Status;

@Path("/auth")
public class FakeAuthResource extends BaseResouce {
	
	@GET
	@Path("stub")
	public Object stubConnect(@QueryParam("id") String id) {
		UserDao dao = Daos.get(UserDao.class);

		User user = dao.findById(id);
		if (user != null) {
			request.getSession().setAttribute("user", user);
			request.getSession().setMaxInactiveInterval(3600); // 1 hora
		} else {
			return Response.status(Status.ERROR).build();
		}

		return Response.temporaryRedirect(UriBuilder.fromResource(HtmlResource.class).build()).build();
		//		return true;
	}
	
	@GET
	@Path("logout")
	public Object logout() {
		request.getSession().removeAttribute("user");
		return Response.temporaryRedirect(UriBuilder.fromResource(HtmlResource.class).build()).build();
	}

}
