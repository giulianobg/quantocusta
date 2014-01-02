package sb.quantocusta;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.yammer.dropwizard.config.Configuration;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class QuantoCustaConfiguration extends Configuration {
	
	@Valid
	@NotNull
	private MongoConfiguration mongo;
	
	public MongoConfiguration getMongo() {
		return mongo;
	}
	
	public void setMongo(MongoConfiguration mongo) {
		this.mongo = mongo;
	}
	
}
