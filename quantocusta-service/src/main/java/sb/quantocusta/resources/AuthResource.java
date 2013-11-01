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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.io.IOUtils;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FacebookApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sb.quantocusta.api.User;
import sb.quantocusta.dao.Daos;
import sb.quantocusta.dao.UserDao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yammer.dropwizard.auth.Auth;

@Path("/auth")
public class AuthResource {

	static Logger LOG = LoggerFactory.getLogger(AuthResource.class);

	private static final String FB_APP_ID = "479032988828474"; 
	private static final String FB_APP_SECRET = "174f93c3a563214dd805d19a9bfbd89c";

		@GET
		@Path("token")
		public Object token(@Context HttpServletRequest request) {
			String xxx = "OAuth realm=\"The secret code\"," +
               "oauth_consumer_key=\"9djdj82h48djs9d2\"," +
               "oauth_token=\"kkk9d7dh3k39sjv7\"," +
               "oauth_signature_method=\"HMAC-SHA1\"," + 
               "oauth_timestamp=\"137131201\"," + 
               "oauth_nonce=\"7d8f3e4a\"," +
               "oauth_signature=\"bYT5CMsGcbgUdFHObYMEfcx6bsw%3D\"";
			
			
			return Response.status(200).entity("teste").
					header("Authorization", xxx).
					build();
			
//			OAuth realm="Example"
			
	//		FacebookApi.class
	//		
	//		try {
	//	         //dynamically recognize an OAuth profile based on request characteristic (params,
	//	         // method, content type etc.), perform validation
	//	         OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);
	//	 
	//	         validateRedirectionURI(oauthRequest)
	//	 
	//	         //build OAuth response
	//	         OAuthResponse resp = OAuthASResponse
	//	             .authorizationResponse(HttpServletResponse.SC_FOUND)
	//	             .setCode(oauthIssuerImpl.authorizationCode())
	//	             .location(ex.getRedirectUri())
	//	             .buildQueryMessage();
	//	 
	//	         response.sendRedirect(resp.getLocationUri());
	//	 
	//	         //if something goes wrong
	//	    } catch(OAuthProblemException ex) {
	//	         final OAuthResponse resp = OAuthASResponse
	//	             .errorResponse(HttpServletResponse.SC_FOUND)
	//	             .error(ex)
	//	             .location(redirectUri)
	//	             .buildQueryMessage();
	//	 
	//	         response.sendRedirect(resp.getLocationUri());
	//	    }
	//		
	//		
	////		new Credential.Builder(new AccessMethod()).build()
	////		new AppEngineCredentialStore().s
	//		
	////		BearerToken.
	////		Credential credential =
	////				new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
	////		HttpRequestFactory requestFactory = transport.createRequestFactory(credential);
	////		return requestFactory.buildGetRequest(url).execute();
	//		
	//		System.out.println("AuthResource.token()");
	//		return null;
		}

	@GET
	@Path("connect2")
	public Object connect2() {
		OAuthService service = new ServiceBuilder().
				provider(FacebookApi.class).
				apiKey(FB_APP_ID).
				apiSecret(FB_APP_SECRET).
				callback("http://m.quantocusta.cc/auth/connect").
				build();
		
		Token requestToken = service.getRequestToken();
		
		String authUrl = service.getAuthorizationUrl(requestToken);
		System.out.println(authUrl);
		
		Verifier v = new Verifier("verifier you got from the user");
		Token accessToken = service.getAccessToken(requestToken, v); // the requestToken you had from step 2
		
		OAuthRequest request = new OAuthRequest(Verb.GET, "https://graph.facebook.com/me?id,email,name");
		service.signRequest(accessToken, request); // the access token from step 4
		org.scribe.model.Response response = request.send();
		System.out.println(response.getBody());
		
		return null;
	}

	@GET
	@Path("protected")
	public Object testOAuth(@Auth User user) {
		System.out.println("AuthResource.testOAuth()");
		return null;
	}

	@GET
	@Path("stub")
	public Object stubConnect(@QueryParam("id") String id, 
			@Context HttpServletRequest request) {
		UserDao dao = Daos.get(UserDao.class);

		User user = dao.findById(id);
		System.out.println(user);

		if (user != null) {
			request.getSession().setAttribute("user", user);
			request.getSession().setMaxInactiveInterval(3600); // 1 hora
		}

		return Response.temporaryRedirect(UriBuilder.fromResource(HtmlResource.class).build()).build();
		//		return true;
	}

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

				LOG.debug(node.toString());
				LOG.debug("\n\n");
				LOG.debug(node.get("id").asText());
				LOG.debug("\n\n");

				//				User user = new User();


				LOG.debug(request.toString());
				System.out.println(request.getSession());
				request.getSession().setAttribute("user", "Giuliano");
				System.out.println(request.getSession());



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
