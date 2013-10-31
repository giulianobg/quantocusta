package sb.quantocusta.resources;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/auth")
public class AuthResource {
	
	static Logger LOG = LoggerFactory.getLogger(AuthResource.class);
	
	private static final String FB_APP_ID = "479032988828474"; 
	private static final String FB_APP_SECRET = "174f93c3a563214dd805d19a9bfbd89c";
	
	@GET
	@Path("connect")
	public Object facebookCallback(@QueryParam("code") String code, @Context HttpServletRequest request) {
		if (code != null) {
			try {
				String r = "https://graph.facebook.com/oauth/access_token" + 
						"?client_id=" + FB_APP_ID + 
						"&redirect_uri=" + URLEncoder.encode("http://m.quantocusta.cc/auth/connect", "UTF-8") + 
						"&client_secret=" + FB_APP_SECRET + 
						"&code=" + code;
				
				InputStream rIs = new URL(r).openStream();
				String accessToken = IOUtils.toString(rIs);
				rIs.close();
			
				String reqUrl = "https://graph.facebook.com/me?id,email,name&" + accessToken;
				JsonNode node = new ObjectMapper().readTree(new URL(reqUrl));
				
				LOG.debug(node.asText());
				LOG.debug("\n\n");
				LOG.debug(node.get("id").asText());
				LOG.debug("\n\n");
				
				LOG.debug(request.toString());
//				System.out.println(request.getSession());
//				request.getSession().setAttribute("user", "Giuliano");
//				System.out.println(request.getSession());
				
//				String fb = jsonFb.getString("id");
//				String email = jsonFb.getString("email");
//				String name = jsonFb.getString("name");
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
