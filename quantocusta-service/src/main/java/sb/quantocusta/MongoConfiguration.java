package sb.quantocusta;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

public class MongoConfiguration extends Configuration {

	@JsonProperty @NotEmpty
	public String host;

	@Min(1)
	@Max(65535)
	@JsonProperty
	public int port;

	@JsonProperty @NotEmpty
	public String db;
	
	@JsonProperty @NotEmpty
	public String user;
	
	@JsonProperty @NotEmpty
	public String pwd;
	
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
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
