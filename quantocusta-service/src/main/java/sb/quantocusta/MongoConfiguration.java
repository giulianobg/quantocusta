package sb.quantocusta;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import com.yammer.dropwizard.config.Configuration;

public class MongoConfiguration extends Configuration {

	@JsonProperty @NotEmpty
	public String host = "68.169.54.122";

	@Min(1)
	@Max(65535)
	@JsonProperty
	public int port = 27017;

	@JsonProperty @NotEmpty
	public String db = "quantocusta-dev";
	
	public String getHost() {
		return host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public String getDb() {
		return db;
	}
	
	public void setDb(String db) {
		this.db = db;
	}
	
}
