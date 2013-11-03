package sb.quantocusta.auth;

import sb.quantocusta.api.User;

import com.google.common.base.Optional;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;
import com.yammer.dropwizard.auth.basic.BasicCredentials;

public class BasicAuthenticator implements Authenticator<BasicCredentials, User> {
	
	public Optional<User> authenticate(BasicCredentials arg0)
			throws AuthenticationException {
		
		System.out.println(arg0);
		// TODO Auto-generated method stub
		return null;
	}
    
}
