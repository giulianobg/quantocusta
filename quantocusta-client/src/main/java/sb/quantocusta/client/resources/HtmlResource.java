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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
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
		return new SimplePageView("/assets/tpl/index.ftl", me);
	}
	
	@GET
	@Path("me")
	public View me() {
		// Tudo isso funciona, se quiser usar o access_token para buscar direto da api
		String token = (String) request.getSession().getAttribute("access_token");
		
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
		
		User user = (User) request.getSession().getAttribute("user");
		
		SimplePageView page = new HomeView(user);
		
		// Load venues próximos
		URI uri = UriBuilder.fromUri(configuration.getApi()).
				path("/api/venue/near").
				queryParam("lat", request.getSession().getAttribute("lat")).
				queryParam("lng", request.getSession().getAttribute("lng")).
				queryParam("access_token", token).
				build();
		
		DataResponse response = client.resource(uri).accept(
		        MediaType.APPLICATION_JSON).
		        get(DataResponse.class);
		
		List list = mapper.convertValue(response.getResult(), List.class);
		
		System.out.println(list.size());
		
		List<Venue> venues = new ArrayList<Venue>();
		for (int i = 0; i < list.size(); i++) {
			venues.add(mapper.convertValue(list.get(i), Venue.class));
		}
		page.setVenues(venues);
		
		return page;
	}

	@GET
	@Path("buscar")
	public View search(@QueryParam("q") String q) {
		SimplePageView page = new SearchView();
		
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
			return page;
		}
		
		return new ErrorView();
	}
	
}
