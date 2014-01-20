package sb.quantocusta.client.resources;

import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import sb.quantocusta.resources.BaseResouce;

/**
 * 
 * @author Giuliano Griffante
 *
 */
@Path("/geo")
public class GeoLocationResource extends BaseResouce {
	
	@PUT
	@Path("location")
	public void setLocation(@FormParam("lat") String lat, @FormParam("lng") String lng) {
		request.getSession().setAttribute("lat", lat);
		request.getSession().setAttribute("lng", lng);
	}

}
