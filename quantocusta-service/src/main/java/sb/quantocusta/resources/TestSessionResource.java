package sb.quantocusta.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Path("/session")
public class TestSessionResource {
	
	@GET
	@Path("test")
	public Object test(@Context HttpServletRequest request) {
		System.out.println(request);
		System.out.println(request.getSession());
		
		return "ok";
	}

}
