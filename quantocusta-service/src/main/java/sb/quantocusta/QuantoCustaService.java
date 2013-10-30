package sb.quantocusta;

import java.net.UnknownHostException;

import sb.quantocusta.dao.CategoryDao;
import sb.quantocusta.dao.CityDao;
import sb.quantocusta.dao.Daos;
import sb.quantocusta.dao.VenueDao;
import sb.quantocusta.health.MongoHealthCheck;
import sb.quantocusta.resources.GibaResource;
import sb.quantocusta.resources.HtmlResource;
import sb.quantocusta.resources.api.ApiCategoryResource;
import sb.quantocusta.resources.api.ApiVenueResource;
import sb.quantocusta.resources.api.ApiVoteResource;
import sb.quantocusta.resources.api.Apis;

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

	private QuantoCustaConfiguration configuration;

	public QuantoCustaService() {

	}

	public void initialize(Bootstrap<QuantoCustaConfiguration> bootstrap) {
		bootstrap.setName("quantocusta-app");
		bootstrap.addBundle(new ViewBundle());

		bootstrap.addBundle(new AssetsBundle());
		
		//		bootstrap.addBundle(new AssetsBundle("/assets"));
		//		bootstrap.addBundle(new AssetsBundle("/views"));
	}

	public void run(QuantoCustaConfiguration configuration, Environment environment) {
		this.configuration = configuration;

		/* Cache */
//		Cache<String, Object> venues = CacheBuilder.newBuilder()
//				.maximumSize(1000)
//				.expireAfterWrite(10, TimeUnit.MINUTES)
//				.build();

		/* MongoDB */
//		JacksonDBCollection<Venue, String> venuesColl = null;
		
		DB db = null;
		try {
			MongoClient client = new MongoClient(configuration.getMongo().getHost(), configuration.getMongo().getPort());
			db = client.getDB(configuration.getMongo().getDb());
			
			MongoManaged mongoManaged = new MongoManaged(client);
			environment.manage(mongoManaged);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		/* DAOs */
		Daos.addDao(new CategoryDao(db));
		Daos.addDao(new CityDao(db));
		Daos.addDao(new VenueDao(db));
		
		/* APIs */
		Apis.addApi("category", new ApiCategoryResource(db));
		Apis.addApi("venue", new ApiVenueResource(db));
		Apis.addApi("vote", new ApiVoteResource(db));
		
		/* Resources */
		environment.addResource(Apis.get("category"));
		environment.addResource(Apis.get("venue"));
		environment.addResource(Apis.get("vote"));
		
		environment.addResource(new HtmlResource());
		environment.addResource(new GibaResource()); // :)
		
//		environment.addProvider(new OAuthProvider<User>(new ExampleAuthenticator(),
//                "SUPER SECRET STUFF"));
		
		/* Health checkers */
		environment.addHealthCheck(new MongoHealthCheck(null));
		
	}
	
	public QuantoCustaConfiguration getConfiguration() {
		return configuration;
	}

	public static void main(String[] args) throws Exception {
		new QuantoCustaService().run(args);
	}

}
