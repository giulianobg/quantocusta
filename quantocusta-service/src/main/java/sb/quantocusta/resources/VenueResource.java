package sb.quantocusta.resources;

import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import sb.quantocusta.core.Saying;
import sb.quantocusta.views.HomeView;

import com.google.common.base.Optional;
import com.yammer.metrics.annotation.Timed;

@Path("/local/{city}/{id}")
@Produces(value={MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
public class VenueResource {
	private final AtomicLong counter;

	public VenueResource() {
		this.counter = new AtomicLong();
	}

//	@GET
//	@Timed
//	public HomeView index(@QueryParam("name") Optional<String> name) {
//		return new HomeView();
//	}
}
