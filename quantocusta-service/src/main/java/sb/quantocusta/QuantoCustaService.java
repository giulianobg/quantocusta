package sb.quantocusta;

import java.util.concurrent.TimeUnit;

import sb.quantocusta.health.MongoHealthCheck;
import sb.quantocusta.resources.HomeResource;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Configuration;
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
		
		System.out.println("QuantoCustaService.initialize()");
	}

	public void run(QuantoCustaConfiguration configuration, Environment environment) {
		this.configuration = configuration;

		System.out.println("QuantoCustaService.run()");
		
		/* Health checkers */
		environment.addHealthCheck(new MongoHealthCheck(null));

		/* Resources */
		environment.addResource(new HomeResource());


		/* Cache */
		Cache<String, Object> venues = CacheBuilder.newBuilder()
				.maximumSize(1000)
				.expireAfterWrite(10, TimeUnit.MINUTES)
				.build();
	}
	
	public static void main(String[] args) throws Exception {
		new QuantoCustaService().run(args);
		//		new QuantoCustaService().run(null, new String[]{"server", "src/main/resources/config.yaml"});
	}

}
