package sb.quantocusta.resources;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;

/**
 * 
 * @author Giuliano Griffante
 *
 */
@Path("/geo")
public class GeoLocationResource extends BaseResouce {
	
	@PUT
	@Path("location")
	public void setLocation() {
		System.out.println("GeoLocationResource.setLocation()");
	}

}
