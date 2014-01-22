package sb.quantocusta.client.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import sb.quantocusta.api.DataResponse;
import sb.quantocusta.api.Venue;
import sb.quantocusta.client.QuantoCustaClientConfiguration;
import sb.quantocusta.resources.BaseResouce;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.yammer.dropwizard.jersey.params.IntParam;

@Path("/api")
@Produces("application/json; charset=utf-8")
public class JsonResource extends BaseResouce {

	private QuantoCustaClientConfiguration configuration;

	private Client client;
	private ObjectMapper mapper;

	public JsonResource(QuantoCustaClientConfiguration configuration) {
		this.configuration = configuration;
		client = Client.create();
		mapper = new ObjectMapper();
	}
	
	@GET
	@Path("near")
	public Response near() {
		List<Venue> venues = (List<Venue>) request.getSession().getAttribute("list_me");
		if (venues == null) {
			String token = (String) request.getSession().getAttribute("access_token");
			
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
			
			venues = new ArrayList<Venue>();
			for (int i = 0; i < list.size(); i++) {
				venues.add(mapper.convertValue(list.get(i), Venue.class));
			}
			
			// adiciona a cache (sessao do usuário)
			request.getSession().setAttribute("list_me", venues);
		}
		
		return Response.ok(DataResponse.build(venues)).build();
	}

	@GET
	@Path("venue/{id}")
	public Response venue(@PathParam("id") String id) {
		String token = (String) request.getSession().getAttribute("access_token");

		// Load venue
		URI uri = null;

		if (token != null) {
			uri = UriBuilder.fromUri(configuration.getApi()).
					path("/api/venue/{id}").
					queryParam("access_token", token).
					build(id);
		} else {
			uri = UriBuilder.fromUri(configuration.getApi()).
					path("/api/venue/{id}").
					build(id);
		}

		DataResponse response = client.resource(uri).accept(
				MediaType.APPLICATION_JSON).
				get(DataResponse.class);

		return Response.ok(response).build();

	}
	
	@POST
	@Path("vote")
	public Response vote(@FormParam("id") String id, @FormParam("kind") String kind, @FormParam("v") IntParam v) {
		String token = (String) request.getSession().getAttribute("access_token");
		
		// cria sessao persistente
		MultivaluedMap<String, String> formParamsSession = new MultivaluedMapImpl();
		formParamsSession.add("id", id);
		formParamsSession.add("kind", kind);
		formParamsSession.add("v", String.valueOf(v.get()));
		
		URI uri0 = UriBuilder.fromUri(configuration.getApi()).
				path("/api/vote").
				queryParam("access_token", token).
				build();
		
		DataResponse response = client.resource(uri0).
				type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).
				accept(MediaType.APPLICATION_JSON_TYPE).
				post(DataResponse.class, formParamsSession);
		
		return Response.ok(response).build();
	}

	@POST
	@Path("vote/price")
	public Response submitPrice(@FormParam("id") String id, @FormParam("price") Double price) {
		String token = (String) request.getSession().getAttribute("access_token");
		
		System.out.println(token);
		
		// cria sessao persistente
		MultivaluedMap<String, String> formParamsSession = new MultivaluedMapImpl();
		formParamsSession.add("id", id);
		formParamsSession.add("price", String.valueOf(price));
		
		URI uri0 = UriBuilder.fromUri(configuration.getApi()).
				path("/api/vote/price").
				queryParam("access_token", token).
				build();
		
		DataResponse response = client.resource(uri0).
				type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).
				accept(MediaType.APPLICATION_JSON_TYPE).
				post(DataResponse.class, formParamsSession);
		
		return Response.ok(response).build();

//		return Response.status(Status.FORBIDDEN).entity(DataResponse.build(Status.FORBIDDEN.getStatusCode())).build();
	}
	
	@POST
	@Path("comment")
	public Response submitComment(@FormParam("id") String id, @FormParam("comment") String comment) {
		String token = (String) request.getSession().getAttribute("access_token");
		
		MultivaluedMap<String, String> formParams = new MultivaluedMapImpl();
		formParams.add("id", id);
		formParams.add("comment", comment);
		
		System.out.println(id);
		System.out.println(comment);
		
		URI uri0 = UriBuilder.fromUri(configuration.getApi()).
				path("/api/comment").
				queryParam("access_token", token).
				build();
		
		DataResponse response = client.resource(uri0).
				type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).
				accept(MediaType.APPLICATION_JSON_TYPE).
				post(DataResponse.class, formParams);
		
		return Response.ok(response).build();
	}

}
