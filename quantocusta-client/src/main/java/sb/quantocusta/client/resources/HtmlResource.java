package sb.quantocusta.client.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.lang3.StringUtils;

import sb.quantocusta.api.DataResponse;
import sb.quantocusta.api.User;
import sb.quantocusta.api.Venue;
import sb.quantocusta.client.QuantoCustaClientConfiguration;
import sb.quantocusta.client.views.ErrorView;
import sb.quantocusta.client.views.HomeView;
import sb.quantocusta.client.views.SearchView;
import sb.quantocusta.client.views.SimplePageView;
import sb.quantocusta.client.views.VenueView;
import sb.quantocusta.resources.BaseResouce;
import sb.quantocusta.util.TokenUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.yammer.dropwizard.views.View;

@Path("/")
@Produces("text/html; charset=utf-8")
public class HtmlResource extends BaseResouce {
	
	private QuantoCustaClientConfiguration configuration;
	
	private Client client;
	private ObjectMapper mapper;
	
	public HtmlResource(QuantoCustaClientConfiguration configuration) {
		this.configuration = configuration;
		client = Client.create();
		mapper = new ObjectMapper();
	}
	
	@GET
	public SimplePageView home() {
		User me = (User) request.getSession().getAttribute("user");
		
		SimplePageView page = new SimplePageView("/assets/tpl/index.ftl", me);
		page.addParam("logout", "false");
		page.addParam("callbackHost", configuration.getAuthCallback().contains("prev.quantocusta") ? "prev" : "m");
		
		return page;
	}
	
	@GET
	@Path("discovery")
	public View discovery() {
		// cria sessao persistente
		String id = "52eab2dbdde5c82a3acd8495"; // qc dummy user
		
		MultivaluedMap<String, String> formParamsSession = new MultivaluedMapImpl();
		formParamsSession.add("id", id);
		formParamsSession.add("lat", (String) request.getSession().getAttribute("lat"));
		formParamsSession.add("lng", (String) request.getSession().getAttribute("lng"));
		
		URI uri0 = UriBuilder.fromUri(configuration.getApi()).
				path("/api/session/create").
				build();
		
		client.resource(uri0).
				type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).
				accept(MediaType.APPLICATION_JSON_TYPE).
				post(DataResponse.class, formParamsSession);

		request.getSession().setAttribute("access_token", TokenUtils.tokenFromId(id));
		
		HomeView page = new HomeView();
		page.setRequest(request);
		
		return page;
	}
	
	@GET
	@Path("me")
	public View me() {
		User user = (User) request.getSession().getAttribute("user");
		HomeView page = new HomeView(user);
		page.setRequest(request);
		
//		List<Venue> venues = (List<Venue>) request.getSession().getAttribute("list_me");
//		if (venues == null) {
//			// Tudo isso funciona, se quiser usar o access_token para buscar direto da api
//			String token = (String) request.getSession().getAttribute("access_token");
			
	//		URI uri = UriBuilder.fromUri(configuration.getApi()).
	//				path("/api/user/me").
	//				queryParam("access_token", token).
	//				build();
	//		
	//		WebResource target = client.resource(uri);
	//		
	//		DataResponse response = target.accept(
	//		        MediaType.APPLICATION_JSON).
	//		        get(DataResponse.class);
	//		
	//		User user = mapper.convertValue(response.getResult(), User.class);
	//		
	//		URI uri = UriBuilder.fromUri(configuration.getApi()).
	//				path("/api/venue/me").
	//				queryParam("access_token", token).
	//				build();
	//		
	//		WebResource target = client.resource(uri);
	//		
	//		DataResponse response = target.accept(
	//		        MediaType.APPLICATION_JSON).
	//		        get(DataResponse.class);
	//		
	//		User user = mapper.convertValue(response.getResult(), User.class);
			
			// Load venues próximos
//			URI uri = UriBuilder.fromUri(configuration.getApi()).
//					path("/api/venue/near").
//					queryParam("lat", request.getSession().getAttribute("lat")).
//					queryParam("lng", request.getSession().getAttribute("lng")).
//					queryParam("access_token", token).
//					build();
//			
//			DataResponse response = client.resource(uri).accept(
//			        MediaType.APPLICATION_JSON).
//			        get(DataResponse.class);
//			
//			List list = mapper.convertValue(response.getResult(), List.class);
//			
//			venues = new ArrayList<Venue>();
//			for (int i = 0; i < list.size(); i++) {
//				venues.add(mapper.convertValue(list.get(i), Venue.class));
//			}
//			
//			// adiciona a cache (sessao do usuário)
//			request.getSession().setAttribute("list_me", venues);
//		}
//		
//		page.setVenues(venues);
		
		return page;
	}

	@GET
	@Path("buscar")
	public View search(@QueryParam("q") String q) {
		SimplePageView page = new SearchView();
		page.setMe((User) request.getSession().getAttribute("user"));
		
		if (StringUtils.isEmpty(q)) {
			return page;
		} else {
//			String lat = queryParam("lat", request.getSession().getAttribute("lat") == null ?
//					auth : queryParam("lat", request.getSession().getAttribute("lat")
			
			URI uri = UriBuilder.fromUri(configuration.getApi()).
					path("/api/venue/search").
					queryParam("q", q.replaceAll(" ", "+")).
					queryParam("lat", request.getSession().getAttribute("lat")).
					queryParam("lng", request.getSession().getAttribute("lng")).
					build();
			
			WebResource target = client.resource(uri);
			
			DataResponse response = target.accept(
			        MediaType.APPLICATION_JSON).
			        get(DataResponse.class);
	
			List list = mapper.convertValue(response.getResult(), List.class);
			
			List<Venue> venues = new ArrayList<Venue>();
			for (int i = 0; i < list.size(); i++) {
				venues.add(mapper.convertValue(list.get(i), Venue.class));
			}
	
			page.setVenues(venues);
		}
		return page;
	}
	
	@GET
	@Path("local/{id}")
	public View get(@PathParam("id") String id) {
//		String token = (String) request.getSession().getAttribute("access_token");
		
		URI uri = UriBuilder.fromUri(configuration.getApi()).path("/api/venue/{id}").build(id);
		
		DataResponse response = client.resource(uri).
//				queryParam("access_token", token).
				accept(MediaType.APPLICATION_JSON).
		        get(DataResponse.class);
		
		Venue venue = mapper.convertValue(response.getResult(), Venue.class);
		
		if (venue != null) {
			SimplePageView page = new VenueView(venue);
			page.setRequest(request);
			page.setMe((User) request.getSession().getAttribute("user"));
			return page;
		}
		
		return new ErrorView();
	}
	
	@GET
	@Path("local/thrd/{id}")
	public View getThirdy(@PathParam("id") String id) {
		URI uri = UriBuilder.fromUri(configuration.getApi()).path("/api/venue/thrd/{id}").build(id);
		
		DataResponse response = client.resource(uri).
				accept(MediaType.APPLICATION_JSON).
		        get(DataResponse.class);
		
		 Venue venue = mapper.convertValue(response.getResult(), Venue.class);
		
		if (venue != null) {
			SimplePageView page = new VenueView(venue);
			page.setRequest(request);
			page.setMe((User) request.getSession().getAttribute("user"));
			return page;
		}
		
		return new ErrorView();
	}
	
	@GET
	@Path("sobre")
	public View about() {
		return new SimplePageView("/assets/tpl/about.ftl", (User) request.getSession().getAttribute("user"));
	}
	
	@GET
	@Path("config")
	public View settings() {
		return new SimplePageView("/assets/tpl/settings.ftl", (User) request.getSession().getAttribute("user"));
	}
	
	@GET
	@Path("sair")
	public View logout() {
		request.getSession().invalidate(); // limpa sessão
		
		SimplePageView page = new SimplePageView("/assets/tpl/index.ftl");
		page.addParam("logout", "true");
		page.addParam("callbackHost", configuration.getAuthCallback().contains("prev.quantocusta") ? "prev" : "m");
		
		return page;
	}
	
}
