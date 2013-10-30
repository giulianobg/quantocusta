package sb.quantocusta;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.config.HttpConfiguration;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class QuantoCustaConfiguration extends Configuration {
	
	@Valid
	@NotNull
	private MongoConfiguration mongo;
	
	@Valid
	private HttpConfiguration http;
	
	public MongoConfiguration getMongo() {
		return mongo;
	}
	
	public void setMongo(MongoConfiguration mongo) {
		this.mongo = mongo;
	}
	
	public HttpConfiguration getHttp() {
		return http;
	}
	
	public void setHttp(HttpConfiguration http) {
		this.http = http;
	}

}
