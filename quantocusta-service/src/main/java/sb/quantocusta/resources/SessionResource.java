package sb.quantocusta.resources;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sb.quantocusta.api.temp.Session;
import sb.quantocusta.dao.Daos;
import sb.quantocusta.dao.SessionDao;
import sb.quantocusta.util.TokenUtils;

/**
 * 
 * @author Giuliano Griffante
 *
 */
@Path("/api/session")
@Produces("application/json; charset=utf-8")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class SessionResource extends BaseResouce {
	
	static Logger LOG = LoggerFactory.getLogger(SessionResource.class);
	
	public SessionResource() {
	}
	
	@POST
	@Path("create")
	public Response create(@FormParam("id") String id, @QueryParam("lat") String lat, @QueryParam("lng") String lng) {
		String token = TokenUtils.tokenFromId(id);
		
		SessionDao dao = Daos.get(SessionDao.class);
		
		// invalida sessoes antigas
//		DBObject q = new DBObject();
//		q.is("user", "");
//		dao.find(q);
		
		Session s = new Session();
		s.setUserId(id);
		s.setAccessToken(token);
		s.setStatus(sb.quantocusta.api.temp.Status.VALID);
		s.setCreatedAt(new Date());
		
		dao.update(s);
		
//		URI uri = UriBuilder.fromResource(UserResource.class).
				
		
		return null;//me(token);
	}
//	
//	@PUT
//	@Path("update")
//	@Consumes
//	public String update() {
//		return "update";
//	}
//	
//	@DELETE
//	@Path("delete")
//	public Object delete() {
//		return "del";
//	}

}
