package sb.quantocusta.resources;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.mongojack.DBCursor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sb.quantocusta.api.DataResponse;
import sb.quantocusta.api.temp.Session;
import sb.quantocusta.dao.Daos;
import sb.quantocusta.dao.SessionDao;
import sb.quantocusta.util.TokenUtils;

import com.mongodb.BasicDBObject;

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
	public Response create(@FormParam("id") String id, @FormParam("lat") String lat, @FormParam("lng") String lng) {
		String token = TokenUtils.tokenFromId(id);

		SessionDao dao = Daos.get(SessionDao.class);

		// invalida sessoes antigas
		BasicDBObject query = new BasicDBObject("access_token", token);
		DBCursor<Session> cursor = dao.findAll(query);
		try {
			while (cursor.hasNext()) {
				Session s = cursor.next();
				s.setStatus(sb.quantocusta.api.temp.Status.EXPIRED);
				dao.removeById(s.getId());
			}
		} finally {
			cursor.close();
		}

		Session s = new Session();
		s.setUserId(id);
		s.setAccessToken(token);
		s.setLat(lat);
		s.setLng(lng);
		s.setStatus(sb.quantocusta.api.temp.Status.VALID);
		s.setCreatedAt(new Date());

		s = dao.update(s);

		return Response.ok(DataResponse.build(s)).build();
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
