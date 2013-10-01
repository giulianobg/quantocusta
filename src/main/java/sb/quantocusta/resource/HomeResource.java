package sb.quantocusta.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.common.base.Optional;
import com.yammer.metrics.annotation.Timed;

@Path("/")
@Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})
public class HomeResource {
	
	public HomeResource() {
		// TODO Auto-generated constructor stub
	}
	
	@GET
    @Timed
    public void sayHello(@QueryParam("name") Optional<String> name) {
		
    }

}
