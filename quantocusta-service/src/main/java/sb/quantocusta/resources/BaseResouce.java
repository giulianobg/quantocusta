package sb.quantocusta.resources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class BaseResouce {
	
	@Context
	protected HttpServletRequest request;
	
	@Context
	protected HttpServletResponse response;
	
	@Context
	protected UriInfo uriInfo;

}
