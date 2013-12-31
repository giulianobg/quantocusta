package sb.quantocusta.client;

import org.eclipse.jetty.server.session.SessionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sb.quantocusta.client.health.TemplateHealthCheck;
import sb.quantocusta.client.resources.AuthResource;
import sb.quantocusta.client.resources.GeoLocationResource;
import sb.quantocusta.client.resources.HtmlResource;
import sb.quantocusta.client.resources.JsonResource;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.ViewBundle;

/**
 * 
 * @author Giuliano Griffante
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
		
		/* Resources */
		environment.addResource(new AuthResource(configuration));
		environment.addResource(new GeoLocationResource());
		environment.addResource(new HtmlResource(configuration));
		environment.addResource(new JsonResource(configuration));
//		environment.addResource(new TestSessionResource());
//		environment.addResource(new GibaResource()); // :)
		
		environment.addProtectedTarget("/assets/tpl/");
		
		/* Health checkers */
		environment.addHealthCheck(new TemplateHealthCheck(null));
		
	}
	
	public QuantoCustaClientConfiguration getConfiguration() {
		return configuration;
	}

	public static void main(String[] args) throws Exception {
		new QuantoCustaClientService().run(args);
	}

}
