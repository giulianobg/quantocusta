package sb.quantocusta.auth;

import sb.quantocusta.api.User;

import com.google.common.base.Optional;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;

/**
 * 
 * @author Giuliano Griffante
 */
public class QcAuthenticator implements Authenticator<String, User> {

	public Optional<User> authenticate(String credentials)
			throws AuthenticationException {
		
//		com.yammer.dropwizard.auth.basic.
		System.out.println("QcAuthenticator.authenticate()");
		System.out.println(credentials);
		
		// TODO Auto-generated method stub
		return null;
	}

}
