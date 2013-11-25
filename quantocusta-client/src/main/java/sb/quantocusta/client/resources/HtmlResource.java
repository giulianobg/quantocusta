package sb.quantocusta.client.resources;

import java.net.URL;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang3.StringUtils;

import sb.quantocusta.api.DataResponse;
import sb.quantocusta.api.Venue;
import sb.quantocusta.dao.Daos;
import sb.quantocusta.dao.VenueDao;
import sb.quantocusta.resources.BaseResouce;
import sb.quantocusta.resources.api.ApiVenueResource;
import sb.quantocusta.resources.api.Apis;
import sb.quantocusta.resources.thirdy.GeoIP2Api;
import sb.quantocusta.views.SearchView;
import sb.quantocusta.views.SimplePageView;
import sb.quantocusta.views.VenueView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yammer.dropwizard.views.View;

@Path("/")
@Produces("text/html; charset=utf-8")
//@Produces("text/html; charset=iso-8859-1")
public class HtmlResource extends BaseResouce {
	
	@GET
	public SimplePageView home() {
		return new SimplePageView("/assets/tpl/index.ftl");
	}
	
	@GET
	@Path("me")
	public View me() {
		return null;
	}

	@GET
	@Path("buscar")
	public View search(@QueryParam("q") String q, @QueryParam("lat") String lat, @QueryParam("lng") String lng) {
		
//		URI uri = UriBuilder.fromResource(ApiVenueResource.class).path("search").queryParam("q", q).build();
//		System.out.println(Response.created(uri).toString());
//		Response r1 = Response.created(uri).entity(List.class).build();
		
//		Response r = r1.ok().build();
		
//		Response r = Response.created(uri).build();
//		List<Venue> venues = (List<Venue>) Response.created(uri).build().ok();
		
//		System.out.println(venues);
		
//		List<Venue> venues = null;
		
//		ObjectMapper mapper = new ObjectMapper();
		
//		TypeFactory.defaultInstance().constructCollectionType(List.class, Venue.class);
//		try {
//			venues = mapper.readValue(new URL(new URL("http://localhost:8080/"), "/api/venue/search?q=" + q), 
//					TypeFactory.defaultInstance().constructCollectionType(List.class, Venue.class));
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		System.out.println(request.getRemoteAddr());
		
//		String ip = request.getHeader("X-Forwarded-For");  
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
//            ip = request.getHeader("Proxy-Client-IP");  
//        }  
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
//            ip = request.getHeader("WL-Proxy-Client-IP");  
//        }  
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
//            ip = request.getHeader("HTTP_CLIENT_IP");  
//        }  
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
//            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
//        }  
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
//            ip = request.getRemoteAddr();  
//        }
//        
//        System.out.println(ip);
		
		
		if (StringUtils.isEmpty(lat) || StringUtils.isEmpty(lng)) {
			String[] latLng = GeoIP2Api.getInstance().guessLatLng(request.getRemoteAddr());
			if (latLng != null) {
				lat = latLng[0];
				lng = latLng[1];
			} else {
//				new ErrorView();
			}
		}
		
		
		ApiVenueResource resource = Apis.get(ApiVenueResource.class, "venue");
		List<Venue> venues = (List<Venue>) ((DataResponse) resource.search(q, lat, lng).getEntity()).getResult();

		return new SearchView(venues);
	}
	
	@GET
	@Path("local/{id}")
	public View get(@PathParam("id") String id) {
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
//		DataResponse response = mapper.readTree(new URL(""));
		
		return new VenueView(Daos.get(VenueDao.class).findById(id));
	}
	
	@GET
	@Path("local/thrd/{id}")
	public View getThirdy(@PathParam("id") String id) {
//		URI uri = UriBuilder.fromResource(ApiVenueResource.class).path("thrd/{id}").build(id);
		
//		ApiVenueResource resource = Apis.get(ApiVenueResource.class, "venue");
		Venue venue = (Venue) ((DataResponse) resource.findBy3rdId(id, null).getEntity()).getResult();
		
//		if (venue != null) {
//			URI location = UriBuilder.fromResource(HtmlResource.class).path("{id}").build(id);
//			return Response.temporaryRedirect(location);
//		}
		
		return new VenueView(venue);
	}
	
}
