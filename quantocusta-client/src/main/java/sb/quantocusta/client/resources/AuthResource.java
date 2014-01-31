package sb.quantocusta.client.resources;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sb.quantocusta.api.DataResponse;
import sb.quantocusta.api.User;
import sb.quantocusta.client.QuantoCustaClientConfiguration;
import sb.quantocusta.resources.BaseResouce;
import sb.quantocusta.util.TokenUtils;
import ch.qos.logback.core.status.Status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * 
 * @author Giuliano Griffante
 *
 */
@Path("/auth")
@Produces("application/json; charset=utf-8")
public class AuthResource extends BaseResouce {
	
	static Logger LOG = LoggerFactory.getLogger(AuthResource.class);

	private static final String FB_APP_ID = "479032988828474"; 
	private static final String FB_APP_SECRET = "174f93c3a563214dd805d19a9bfbd89c";
	
	private QuantoCustaClientConfiguration configuration;
	
	private Client client;
	private ObjectMapper mapper;
	
	public AuthResource(QuantoCustaClientConfiguration configuration) {
		this.configuration = configuration;
		client = Client.create();
		mapper = new ObjectMapper();
	}
	
	@GET
	@Path("connect")
	public Response facebookCallback(@QueryParam("code") String code, @QueryParam("error") String error) {
		String host = "m";
		if (configuration.getAuthCallback().contains("prev.quantocusta")) {
			System.out.println("AuthResource.facebookCallback()");
			host = "prev";	
		}
		
		if (code != null) {
			try {
				String r = "https://graph.facebook.com/oauth/access_token" + 
						"?client_id=" + FB_APP_ID + 
						"&redirect_uri=" + URLEncoder.encode(configuration.getAuthCallback(), "UTF-8") + 
						"&client_secret=" + FB_APP_SECRET + 
						"&code=" + code;

				InputStream rIs = new URL(r).openStream();
				String accessToken = IOUtils.toString(rIs);
				rIs.close();

				String reqUrl = "https://graph.facebook.com/me?id,email,name&" + accessToken;
				JsonNode node = new ObjectMapper().readTree(new URL(reqUrl));

				String id = node.get("id").asText();
				LOG.debug("User's thirdy party ID is " + id);
				
				// Busca usuário
				URI uri = UriBuilder.fromUri(configuration.getApi()).
						path("/api/user/thrd/{id}").
//						queryParam("access_token", TokenUtils.tokenFromId(id)).
						build(id);
				
				User user = null;
				try {
					DataResponse responseUser = client.resource(uri).get(DataResponse.class);
					user = mapper.convertValue(responseUser.getResult(), User.class);
				} catch (Exception e) {
					user = null;
				}
				
				if (user == null) {
					// Primeiro acesso - salva usuário
					MultivaluedMap<String, String> formParams2 = new MultivaluedMapImpl();
					formParams2.add("email", node.get("email").asText());
					formParams2.add("name", node.get("name").asText());
					formParams2.add("thirdyId", id);
					
					URI uriUser = UriBuilder.fromUri(configuration.getApi()).
							path("/api/user/create").
							queryParam("access_token", TokenUtils.tokenFromId(id)).
							build();
					
					client.resource(uriUser).
							type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).
							accept(MediaType.APPLICATION_JSON_TYPE).
							post(DataResponse.class, formParams2);
					
					DataResponse responseUser = client.resource(uri).get(DataResponse.class);
					user = mapper.convertValue(responseUser.getResult(), User.class);
				}
				
				// cria sessao persistente
				MultivaluedMap<String, String> formParamsSession = new MultivaluedMapImpl();
				formParamsSession.add("id", user.getId());
				formParamsSession.add("lat", (String) request.getSession().getAttribute("lat"));
				formParamsSession.add("lng", (String) request.getSession().getAttribute("lng"));
				
				URI uri0 = UriBuilder.fromUri(configuration.getApi()).
						path("/api/session/create").
						build();
				
				client.resource(uri0).
						type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).
						accept(MediaType.APPLICATION_JSON_TYPE).
						post(DataResponse.class, formParamsSession);

				request.getSession().setAttribute("access_token", TokenUtils.tokenFromId(user.getId()));
				request.getSession().setAttribute("user", user);
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			}
		} else if (error != null) {
			if (StringUtils.equals(error, "access_denied")) { 
				LOG.warn("User hasn't allowed access. Redirecting to home page...");
				return Response.seeOther(UriBuilder.fromUri("http://" + host + ".quantocusta.cc/").build()).build();
			}
		}

		return Response.seeOther(UriBuilder.fromUri("http://" + host + ".quantocusta.cc/me").build()).build();
	}
	
//	@GET
//	@Path("stub_create")
//	public Response stubCreate(@QueryParam("id") String id) {
//		// Busca usuário
//		URI uri = UriBuilder.fromUri(configuration.getApi()).
//				path("/api/user/thrd/{id}").
////				queryParam("access_token", TokenUtils.tokenFromId(id)).
//				build(id);
//		
//		try {
//			DataResponse responseUser = client.resource(uri).get(DataResponse.class);
//			User user = mapper.convertValue(responseUser.getResult(), User.class);
//		} catch (Exception e) {
//			// Primeiro acesso - salva usuário
//			MultivaluedMap<String, String> formParams2 = new MultivaluedMapImpl();
//			formParams2.add("email", RandomStringUtils.random(10) + "@" + RandomStringUtils.random(8) + ".com.br");
//			formParams2.add("name", RandomStringUtils.random(8) + " " + RandomStringUtils.random(10));
//			formParams2.add("thirdyId", id);
//			
//			URI uriUser = UriBuilder.fromUri(configuration.getApi()).
//					path("/api/user/create").
//					queryParam("access_token", TokenUtils.tokenFromId(id)).
//					build();
//			
//			client.resource(uriUser).
//					type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).
//					accept(MediaType.APPLICATION_JSON_TYPE).
//					post(DataResponse.class, formParams2);
//		}
//		
//		return Response.noContent().build();
//	}
	
//	@GET
//	@Path("test_post")
//	public Response test() {
//		// Primeiro acesso - salva usuário
//		MultivaluedMap<String, String> formParams = new MultivaluedMapImpl();
//		formParams.add("email", "giuliano@griffante.com");
//		formParams.add("name", "Joleano");
//		formParams.add("thirdyId", "000");
//		
//		URI uri = UriBuilder.fromUri(configuration.getApi()).
//				path("/api/user/create").
//				queryParam("access_token", TokenUtils.tokenFromId("000")).
//				build();
//		
//		WebResource target = client.resource(uri);
//		
//		DataResponse response = target.
//				type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).
//				accept(MediaType.APPLICATION_JSON_TYPE).
//				post(DataResponse.class, formParams);
//		
//		User user = mapper.convertValue(response.getResult(), User.class);
//		
//		return Response.ok(DataResponse.build(user)).build();
//	}
	
	@GET
	@Path("stub")
	public Response stubConnect(@QueryParam("id") String id) {
		// Busca usuário
		URI uriUser = UriBuilder.fromUri(configuration.getApi()).
				path("/api/user/thrd/{id}").
				//queryParam("access_token", TokenUtils.tokenFromId(id)).
				build(id);
		
		DataResponse responseUser = client.resource(uriUser).get(DataResponse.class);
		User user = mapper.convertValue(responseUser.getResult(), User.class);

		if (user != null) {
			// cria sessao persistente
			MultivaluedMap<String, String> formParamsSession = new MultivaluedMapImpl();
			formParamsSession.add("id", user.getId());
			formParamsSession.add("lat", (String) request.getSession().getAttribute("lat"));
			formParamsSession.add("lng", (String) request.getSession().getAttribute("lng"));
			
			URI uri0 = UriBuilder.fromUri(configuration.getApi()).
					path("/api/session/create").
					build();
			
			client.resource(uri0).
					type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).
					accept(MediaType.APPLICATION_JSON_TYPE).
					post(DataResponse.class, formParamsSession);
			
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("access_token", TokenUtils.tokenFromId(user.getId()));
			request.getSession().setMaxInactiveInterval(7200); // 2 horas
		} else {
			return Response.status(Status.ERROR).build();
		}

		return Response.temporaryRedirect(UriBuilder.fromResource(HtmlResource.class).path("me").build()).build();
	}
	
	@GET
	@Path("sair")
	public Response logout() {
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("access_token");
		return Response.temporaryRedirect(UriBuilder.fromResource(HtmlResource.class).build()).build();
	}

}
