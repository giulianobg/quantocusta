package sb.quantocusta.api.temp;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class AccessToken {
	
	@JsonProperty("access_token")
	private String accessToken;
	
	public AccessToken() {
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
