package sb.quantocusta.resources;

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
	@Path("page")
	@Produces("text/html;charset=UTF-8")
	public HomeView index() {
		return new HomeView();
	}
	
	@GET
	@Path("page2")
	@Produces("text/html;charset=UTF-8")
	public HomeView index2() {
		return new HomeView("home.ftl");
	}
	
	@GET
	@Path("page3")
	@Produces("text/html;charset=UTF-8")
	public HomeView index3() {
		return new HomeView("resources/assets/home.ftl");
	}
	
	@GET
	@Path("page4")
	@Produces("text/html;charset=UTF-8")
	public HomeView index4() {
		return new HomeView("/src/main/resources/assets/home.ftl");
	}
	
//	@GET
//	@Path("json")
//	public Object json() {
//		return new String("coisa");
//	}
	
}
