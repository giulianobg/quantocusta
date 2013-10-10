package sb.quantocusta.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import sb.quantocusta.views.HomeView;

import com.yammer.metrics.annotation.Timed;

@Path("/")
@Produces(value={MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
public class HomeResource {

	public HomeResource() {
	}

	@GET
	@Path("page")
	public HomeView index() {
		return new HomeView();
	}
	
	@GET
	@Path("json")
	public Object json() {
		return new String("coisa");
	}
}
