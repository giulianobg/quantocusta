package sb.quantocusta.auth;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import sb.quantocusta.api.User;
import sb.quantocusta.api.temp.Session;
import sb.quantocusta.api.temp.Status;
import sb.quantocusta.dao.Daos;
import sb.quantocusta.dao.SessionDao;
import sb.quantocusta.dao.UserDao;

import com.google.common.base.Optional;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;

/**
 * 
 * @author Giuliano Griffante
 */
public class QcAuthenticator implements Authenticator<String, User> {

	public Optional<User> authenticate(String accessToken) throws AuthenticationException {
		SessionDao dao = Daos.get(SessionDao.class);
		Session s = dao.find(accessToken);
		
		if (s == null || s.getStatus() == Status.EXPIRED) {
			throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("You have no access to this resource or your access_token was expired.")
                    .type(MediaType.TEXT_PLAIN_TYPE)
                    .build());
		} else {
			UserDao uDao = Daos.get(UserDao.class);
			User user = uDao.findById(s.getUserId());
			
			return Optional.<User>fromNullable(user);
		}
	}

}
