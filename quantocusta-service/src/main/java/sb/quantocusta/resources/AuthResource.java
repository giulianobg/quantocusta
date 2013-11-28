package sb.quantocusta.resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.io.IOUtils;
import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sb.quantocusta.api.User;
import sb.quantocusta.dao.Daos;
import sb.quantocusta.dao.UserDao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yammer.dropwizard.auth.Auth;

@Path("/auth")
public class AuthResource extends BaseResouce {

	static Logger LOG = LoggerFactory.getLogger(AuthResource.class);

	private static final String FB_APP_ID = "479032988828474"; 
	private static final String FB_APP_SECRET = "174f93c3a563214dd805d19a9bfbd89c";
	
	private static final String GOOGLE_APP_ID = ""; 
	private static final String GOOGLE_APP_SECRET = "";
	
	

	//		//	             .authorizationResponse(HttpServletResponse.SC_FOUND)
	//		//	             .setCode(oauthIssuerImpl.authorizationCode())
	//		//	             .location(ex.getRedirectUri())
	//		//	             .buildQueryMessage();
	//		//	 
	//		//	         response.sendRedirect(resp.getLocationUri());
	//		//	 
	//		//	         //if something goes wrong
	//		//	    } catch(OAuthProblemException ex) {
	//		//	         final OAuthResponse resp = OAuthASResponse
	//		//	             .errorResponse(HttpServletResponse.SC_FOUND)
	//		//	             .error(ex)
	//		//	             .location(redirectUri)
	//		//	             .buildQueryMessage();
	//		//	 
	//		//	         response.sendRedirect(resp.getLocationUri());
	//		//	    }
	//		//		
	//		//		
	//		////		new Credential.Builder(new AccessMethod()).build()
	//		////		new AppEngineCredentialStore().s
	//		//		
	//		////		BearerToken.
	//		////		Credential credential =
	//		////				new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
	//		////		HttpRequestFactory requestFactory = transport.createRequestFactory(credential);
	//		////		return requestFactory.buildGetRequest(url).execute();
	//		//		
	//		//		System.out.println("AuthResource.token()");
	//		//		return null;
	//	}
	//
	//	@POST
	//	@Path("/token")
	//	@Produces(MediaType.APPLICATION_JSON)
	//	@Consumes("application/x-www-form-urlencoded")
	//	public Response token(@HeaderParam("Authorization")
	//	String authorization, final MultivaluedMap<String, String> formParameters) {
	//		accessTokenRequest accessTokenRequest = AccessTokenRequest.fromMultiValuedFormParameters(formParameters);
	//		UserPassCredentials credentials = getUserPassCredentials(authorization, accessTokenRequest);
	//		String grantType = accessTokenRequest.getGrantType();
	//		if (GRANT_TYPE_CLIENT_CREDENTIALS.equals(grantType)) {
	//			accessTokenRequest.setClientId(credentials.getUsername());
	//		}
	//		ValidationResponse vr = oAuth2Validator.validate(accessTokenRequest);
	//		if (!vr.valid()) {
	//			return sendErrorResponse(vr);
	//		}
	//		AuthorizationRequest request;
	//		try {
	//			if (GRANT_TYPE_AUTHORIZATION_CODE.equals(grantType)) {
	//				request = authorizationCodeToken(accessTokenRequest);
	//			} else if (GRANT_TYPE_REFRESH_TOKEN.equals(grantType)) {
	//				request = refreshTokenToken(accessTokenRequest);
	//			} else if (GRANT_TYPE_CLIENT_CREDENTIALS.equals(grantType)) {
	//				request =  new AuthorizationRequest();
	//				request.setClient(accessTokenRequest.getClient());
	//				// We have to construct a AuthenticatedPrincipal on-the-fly as there is only key-secret authentication
	//				request.setPrincipal(new AuthenticatedPrincipal(request.getClient().getClientId()));
	//				// Apply all client scopes to the access token.
	//				// TODO: take into account given scopes from the request
	//				request.setGrantedScopes(request.getClient().getScopes());
	//			}
	//			else {
	//				return sendErrorResponse(ValidationResponse.UNSUPPORTED_GRANT_TYPE);
	//			}
	//		} catch (ValidationResponseException e) {
	//			return sendErrorResponse(e.v);
	//		}
	//		if (!request.getClient().isExactMatch(credentials)) {
	//			return Response.status(Status.UNAUTHORIZED).header(WWW_AUTHENTICATE, BASIC_REALM).build();
	//		}
	//		AccessToken token = createAccessToken(request, false);
	//
	//		AccessTokenResponse response = new AccessTokenResponse(token.getToken(), BEARER, request.getClient()
	//				.getExpireDuration(), token.getRefreshToken(), StringUtils.join(token.getScopes(), ','));
	//
	//		return Response.ok().entity(response).build();
	//
	//	}

	@GET
	@Path("authorize2")
	public Object auth(@QueryParam("redirect_url") String redirectUrl,
			@QueryParam("client_id") String clientId) throws IOException, OAuthSystemException {
		
		OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
		
		try {
			//dynamically recognize an OAuth profile based on request characteristic (params,
			// method, content type etc.), perform validation
			OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);

//			validateRedirectionURI(oauthRequest)

			//build OAuth response
			OAuthResponse resp = OAuthASResponse
					.authorizationResponse(request, HttpServletResponse.SC_FOUND)
					.setCode(oauthIssuerImpl.authorizationCode())
					.location(redirectUrl)
					.buildQueryMessage();

//			response.sendRedirect(resp.getLocationUri());
			
			return Response.status(resp.getResponseStatus()).location(UriBuilder.fromUri(resp.getLocationUri()).build()).build();

//			return Response.status(302).location(UriBuilder.fromUri(redirectUrl).build()).entity(resp.getLocationUri()).build();
			
			//if something goes wrong
		} catch (OAuthProblemException ex) {
			final OAuthResponse resp = OAuthASResponse
					.errorResponse(HttpServletResponse.SC_FOUND)
					.error(ex)
					.location(redirectUrl)
					.buildQueryMessage();

			response.sendRedirect(resp.getLocationUri());
			
			return Response.status(response.getStatus()).build();
		} catch (OAuthSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.status(response.getStatus()).build();
	}

	@POST
	@Path("token")
	@Consumes("application/x-www-form-urlencoded")
//	@Produces("application/json")
	public Object ok(@FormParam("client_id") String clientId,
			@FormParam("client_secret") String clientSecret,
			@FormParam("redirect_uri") String redirectUri,
			@FormParam("grant_type") String grant_type,
			@FormParam("scope") String scope) {
		
		OAuthTokenRequest oauthRequest = null;

		OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());

		try {
			oauthRequest = new OAuthTokenRequest(request);

			// validateClient(oauthRequest);

			String authzCode = oauthRequest.getCode();

			// some code
			System.out.println(authzCode);

			String accessToken = oauthIssuerImpl.accessToken();
			String refreshToken = oauthIssuerImpl.refreshToken();

			// some code


			OAuthResponse r = OAuthASResponse
					.tokenResponse(HttpServletResponse.SC_OK)
					.setAccessToken(accessToken)
					.setExpiresIn("3600")
					.setRefreshToken(refreshToken)
					.buildJSONMessage();

//			response.setStatus(r.getResponseStatus());
			PrintWriter pw = response.getWriter();
			pw.print(r.getBody());
			pw.flush();
			pw.close();
			
			return Response.ok(r.getBody()).
					build();

			//if something goes wrong
		} catch(OAuthProblemException ex) {

			ex.printStackTrace();
			
			OAuthResponse r = null;
			try {
				r = OAuthResponse
						.errorResponse(401)
						.error(ex)
						.buildJSONMessage();
				
				response.setStatus(r.getResponseStatus());

				PrintWriter pw = response.getWriter();
				pw.print(r.getBody());
				pw.flush();
				pw.close();
			} catch (OAuthSystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

//			response.sendError(401);
			return Response.status(r.getResponseStatus()).build();
		} catch (OAuthSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.status(401).build();
	}

//	@GET
//	@Path("connect2")
//	public Object connect2(@QueryParam("code") String code) {
//		OAuthService service = new ServiceBuilder().
//				provider(FacebookApi.class).
//				apiKey(FB_APP_ID).
//				apiSecret(FB_APP_SECRET).
//				callback("http://m.quantocusta.cc/auth/connect2").
//				build();
//		
//		Token requestToken = service.getRequestToken();
//
//		String authUrl = service.getAuthorizationUrl(requestToken);
//		System.out.println(authUrl);
//
//		Verifier v = new Verifier("verifier you got from the user");
//		Token accessToken = service.getAccessToken(requestToken, v); // the requestToken you had from step 2
//
//		OAuthRequest request = new OAuthRequest(Verb.GET, "https://graph.facebook.com/me?id,email,name");
//		service.signRequest(accessToken, request); // the access token from step 4
//		org.scribe.model.Response response = request.send();
//		System.out.println(response.getBody());
//
//		return null;
//	}

	@GET
	@Path("protected")
	public Object testOAuth(@Auth User user) {
		System.out.println("AuthResource.testOAuth()");
		return null;
	}

//	@GET
//	@Path("stub")
//	public Object stubConnect(@QueryParam("id") String id) {
//		UserDao dao = Daos.get(UserDao.class);
//
//		User user = dao.findById(id);
//		if (user != null) {
//			request.getSession().setAttribute("user", user);
//			request.getSession().setMaxInactiveInterval(3600); // 1 hora
//		} else {
//			return Response.status(Status.ERROR).build();
//		}
//
//		return Response.temporaryRedirect(UriBuilder.fromResource(HtmlResource.class).build()).build();
//		//		return true;
//	}

	@GET
	@Path("connect")
	public Object facebookCallback(@QueryParam("code") String code) {
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
				
				String id = node.get("id").asText();
				LOG.debug("User's thirdy party ID is " + id);
				
				UserDao dao = Daos.get(UserDao.class);
				User user = dao.findBy3rdId(id);
				if (user == null) {
					// Primeiro acesso
					user = new User();
					user.setEmail(node.get("email").asText());
					user.setName(node.get("name").asText());
					user.setThirdyId(id);
					
					user = dao.insert(user);
				}
				
				request.getSession().setAttribute("user", user);
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			}
		}

		return null;
	}
	
	@GET
	@Path("connect_g")
	public Object googleCallback(@QueryParam("code") String code) {
		if (code != null) {
			try {
				String r = "https://accounts.google.com/o/oauth2" + 
						"?client_id=" + GOOGLE_APP_ID + 
						"&redirect_uri=" + URLEncoder.encode("http://m.quantocusta.cc/auth/connect", "UTF-8") + 
						"&client_secret=" + GOOGLE_APP_SECRET + 
						"&response_type=" + code;

				InputStream rIs = new URL(r).openStream();
				String accessToken = IOUtils.toString(rIs);
				rIs.close();

				String reqUrl = "https://graph.facebook.com/me?id,email,name&" + accessToken;
				JsonNode node = new ObjectMapper().readTree(new URL(reqUrl));
				
				String id = node.get("id").asText();
				LOG.debug("User's thirdy party ID is " + id);
				
				UserDao dao = Daos.get(UserDao.class);
				User user = dao.findBy3rdId(id);
				if (user == null) {
					// Primeiro acesso
					user = new User();
					user.setEmail(node.get("email").asText());
					user.setName(node.get("name").asText());
					user.setThirdyId(id);
					
					user = dao.insert(user);
				}
				
				request.getSession().setAttribute("user", user);
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			}
		}

		return null;
	}

//	@GET
//	@Path("logout")
//	public Object logout() {
//		request.getSession().removeAttribute("user");
//		return Response.temporaryRedirect(UriBuilder.fromResource(HtmlResource.class).build()).build();
//	}
}
