package sb.quantocusta.client;

import org.eclipse.jetty.server.session.SessionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sb.quantocusta.client.resources.AuthResource;
import sb.quantocusta.client.resources.HtmlResource;
import sb.quantocusta.client.resources.OAuthResource;
import sb.quantocusta.resources.api.ApiVenueResource;
import sb.quantocusta.resources.api.ApiVoteResource;
import sb.quantocusta.resources.api.Apis;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.ViewBundle;

/**
 * 
 * @author giuliano.griffante
 *
 */
public class QuantoCustaClientService extends Service<QuantoCustaClientConfiguration> {

	static final Logger LOG = LoggerFactory.getLogger(QuantoCustaClientService.class);
	
	private QuantoCustaClientConfiguration configuration;

	public QuantoCustaClientService() {

	}

	public void initialize(Bootstrap<QuantoCustaClientConfiguration> bootstrap) {
		bootstrap.setName("quantocusta-client-app");
		bootstrap.addBundle(new ViewBundle());

		bootstrap.addBundle(new AssetsBundle());
	}

	public void run(QuantoCustaClientConfiguration configuration, Environment environment) {
		this.configuration = configuration;
		
		environment.setSessionHandler(new SessionHandler());

		/* OAuth2 */
//		environment.addProvider(new OAuthProvider<User>(new QcAuthenticator(), "The secret code"));
//		environment.addProvider(new QcAuthProvider<User>(new QcAuthenticator(), "QuantoCusta-OAuth"));
		
		/* APIs */
		Apis.addApi("venue", new ApiVenueResource());
		Apis.addApi("vote", new ApiVoteResource());
		
		/* Resources */
		environment.addResource(new OAuthResource());
		environment.addResource(new AuthResource());
		environment.addResource(new HtmlResource());
//		environment.addResource(new TestSessionResource());
//		environment.addResource(new GibaResource()); // :)
		
		environment.addProtectedTarget("/assets/tpl/");
		
		/* Health checkers */
		// ??
		
	}
	
	public QuantoCustaClientConfiguration getConfiguration() {
		return configuration;
	}

	public static void main(String[] args) throws Exception {
		new QuantoCustaClientService().run(args);
	}

}
