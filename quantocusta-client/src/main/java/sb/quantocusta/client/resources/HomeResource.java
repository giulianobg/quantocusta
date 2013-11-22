package sb.quantocusta.client.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import sb.quantocusta.views.HomeView;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class HomeResource {

	public HomeResource() {
	}
	
	@GET
	@Produces("text/html; charset=UTF-8")
	public HomeView index() {
		return new HomeView("/assets/m-home.html");
	}

//	@GET
//	@Path("json")
//	public Object json() {
//		return new String("coisa");
//	}
	
}
