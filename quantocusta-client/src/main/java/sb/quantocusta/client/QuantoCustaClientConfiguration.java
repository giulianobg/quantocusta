package sb.quantocusta.client;

import javax.validation.Valid;

import com.yammer.dropwizard.config.Configuration;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class QuantoCustaClientConfiguration extends Configuration {
	
	@Valid
	private String api;
	
	public String getApi() {
		return api;
	}
	
	public void setApi(String api) {
		this.api = api;
	}
	
//	public HttpConfiguration getHttp() {
//		return http;
//	}
//	
//	public void setHttp(HttpConfiguration http) {
//		this.http = http;
//	}

}
