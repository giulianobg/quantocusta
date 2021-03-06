package sb.quantocusta;

import java.net.UnknownHostException;

import org.eclipse.jetty.server.session.SessionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sb.quantocusta.api.User;
import sb.quantocusta.auth.QcAuthProvider;
import sb.quantocusta.auth.QcAuthenticator;
import sb.quantocusta.dao.CategoryDao;
import sb.quantocusta.dao.CityDao;
import sb.quantocusta.dao.CommentDao;
import sb.quantocusta.dao.Daos;
import sb.quantocusta.dao.ReviewDao;
import sb.quantocusta.dao.SessionDao;
import sb.quantocusta.dao.UserDao;
import sb.quantocusta.dao.VenueDao;
import sb.quantocusta.dao.VoteDao;
import sb.quantocusta.health.MongoHealthCheck;
import sb.quantocusta.resources.CommentResource;
import sb.quantocusta.resources.OAuthResource;
import sb.quantocusta.resources.SessionResource;
import sb.quantocusta.resources.UserResource;
import sb.quantocusta.resources.VenueResource;
import sb.quantocusta.resources.VoteResource;

import com.mongodb.DB;
import com.mongodb.MongoClient;
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
public class QuantoCustaService extends Service<QuantoCustaConfiguration> {

	static final Logger LOG = LoggerFactory.getLogger(QuantoCustaService.class);
	
	private QuantoCustaConfiguration configuration;

	public QuantoCustaService() {

	}

	public void initialize(Bootstrap<QuantoCustaConfiguration> bootstrap) {
		bootstrap.setName("quantocusta-api-app");
		bootstrap.addBundle(new ViewBundle());

		bootstrap.addBundle(new AssetsBundle());
	}

	public void run(QuantoCustaConfiguration configuration, Environment environment) {
		this.configuration = configuration;
		
		environment.setSessionHandler(new SessionHandler());

		/* MongoDB */
		DB db = null;
		try {
			MongoClient client = new MongoClient(configuration.getMongo().getHost(), configuration.getMongo().getPort());
			db = client.getDB(configuration.getMongo().getDb());
			//boolean auth = db.authenticate(configuration.getMongo().getUser(), configuration.getMongo().getPwd().toCharArray());
			
			MongoManaged mongoManaged = new MongoManaged(client);
			environment.manage(mongoManaged);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			LOG.error("Cannot connect with the DB :(", e);
			System.exit(1);
		}
		
		/* DAOs */
		Daos.addDao(new CategoryDao(db));
		Daos.addDao(new CityDao(db));
		Daos.addDao(new CommentDao(db));
		Daos.addDao(new ReviewDao(db));
		Daos.addDao(new SessionDao(db));
		Daos.addDao(new UserDao(db));
		Daos.addDao(new VenueDao(db));
		Daos.addDao(new VoteDao(db));
		
		/* OAuth2 */
		environment.addProvider(new QcAuthProvider<User>(new QcAuthenticator(), "QuantoCusta-OAuth"));
		
		/* Resources */
		environment.addResource(new OAuthResource());
		environment.addResource(new CommentResource());
		environment.addResource(new SessionResource());
		environment.addResource(new UserResource());
		environment.addResource(new VenueResource());
		environment.addResource(new VoteResource());
		
		/* Health checkers */
		environment.addHealthCheck(new MongoHealthCheck(null));
		
		/* Cache */
//		Cache<String, String> c1 = CacheBuilder.newBuilder().build();
		
//		c1.getIfPresent(key)
//		Cache<String, String> c = new CacheBuilder<String, String>().build() 
		
	}
	
	public QuantoCustaConfiguration getConfiguration() {
		return configuration;
	}

	public static void main(String[] args) throws Exception {
		new QuantoCustaService().run(args);
	}

}
