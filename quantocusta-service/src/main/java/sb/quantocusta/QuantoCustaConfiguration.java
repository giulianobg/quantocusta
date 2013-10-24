package sb.quantocusta;

import javax.validation.constraints.NotNull;

import com.yammer.dropwizard.config.Configuration;

/**
 * 
 * @author giuliano.griffante
 *
 */
public class QuantoCustaConfiguration extends Configuration {
	
	@NotNull
	private MongoConfiguration mongo;
	
	public MongoConfiguration getMongo() {
		return mongo;
	}
	
	public void setMongo(MongoConfiguration mongo) {
		this.mongo = mongo;
	}

}
