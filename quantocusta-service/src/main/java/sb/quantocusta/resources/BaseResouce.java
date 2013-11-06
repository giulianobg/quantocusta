package sb.quantocusta.resources;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

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

}
