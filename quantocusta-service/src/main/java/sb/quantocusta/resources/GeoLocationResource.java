package sb.quantocusta.resources;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * 
 * @author Giuliano Griffante
 *
 */
@Path("/geo")
public class GeoLocationResource extends BaseResouce {
	
	@PUT
	@Path("location")
	public void setLocation(@QueryParam("latlng") String latlng) {
		request.getSession().setAttribute("latlng", latlng);
//		System.out.println("GeoLocationResource.setLocation()");
	}

}
