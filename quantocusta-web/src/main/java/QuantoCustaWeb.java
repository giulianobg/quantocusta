

import java.net.UnknownHostException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.mongojack.JacksonDBCollection;

import sb.quantocusta.QuantoCustaConfiguration;
import sb.quantocusta.api.Venue;
import sb.quantocusta.health.MongoHealthCheck;
import sb.quantocusta.resources.GibaResource;
import sb.quantocusta.resources.HomeResource;
import sb.quantocusta.resources.api.ApiVenueResource;
import sb.quantocusta.resources.api.ApiVoteResource;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
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
public class QuantoCustaWeb extends Service<QuantoCustaConfiguration> {

	private QuantoCustaConfiguration configuration;

	public QuantoCustaWeb() {

	}

	public void initialize(Bootstrap<QuantoCustaConfiguration> bootstrap) {
		bootstrap.setName("quantocusta-webapp");
		bootstrap.addBundle(new ViewBundle());

		bootstrap.addBundle(new AssetsBundle());
	}

	public void run(QuantoCustaConfiguration configuration, Environment environment) {
		this.configuration = configuration;

		System.out.println(configuration);
		System.out.println(configuration.getMongo());
		System.out.println(configuration.getMongo().getDb());

		/* Health checkers */
		environment.addHealthCheck(new MongoHealthCheck(null));

		/* Cache */
		Cache<String, Object> venues = CacheBuilder.newBuilder()
				.maximumSize(1000)
				.expireAfterWrite(10, TimeUnit.MINUTES)
				.build();

		/* MongoDB */
		JacksonDBCollection<Venue, String> venuesColl = null;
		
		DB db = null;
		try {
			MongoClient client = new MongoClient(configuration.getMongo().getHost(), configuration.getMongo().getPort());
			db = client.getDB(configuration.getMongo().getDb());
			
//			MongoManaged mongoManaged = new MongoManaged(client);
//			environment.manage(mongoManaged);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		/* Resources */
		environment.addResource(new HomeResource());
		environment.addResource(new ApiVenueResource(db));
		environment.addResource(new ApiVoteResource(db));
		environment.addResource(new GibaResource());
	}

	public static void main(String[] args) throws Exception {
		new QuantoCustaWeb().run(args);
		//		new QuantoCustaService().run(null, new String[]{"server", "src/main/resources/config.yaml"});
	}

}
