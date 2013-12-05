package sb.quantocusta.client.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import sb.quantocusta.resources.BaseResouce;

@Path("/session")
public class TestSessionResource extends BaseResouce {
	
	@GET
	@Path("test")
	public Object test(@Context HttpServletRequest request) {
		System.out.println(request);
		System.out.println(request.getSession());
//		request.getSession().geta
		return "ok";
	}

}
