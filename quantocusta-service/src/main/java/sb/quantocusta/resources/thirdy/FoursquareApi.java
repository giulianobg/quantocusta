package sb.quantocusta.resources.thirdy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sb.quantocusta.api.Category;
import sb.quantocusta.api.Venue;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class FoursquareApi {
	
	static Logger LOG = LoggerFactory.getLogger(FoursquareApi.class);

	private static final String HOST = "https://api.foursquare.com/v2";
	
	private static final String TOKEN = "VUSY3KHJITDBLXIFBRVPOD0YCFHIFO4NWOBGPIDW5D0STSYW";
	private static final String VERSION = "20130415";
	
	private static final String FOOD_CATEGORY = "4d4b7105d754a06374d81259";
	
	private static final String SEARCH_VENUE = HOST + "/venues/search?near=${near}&query=${q}&limit=30&categoryId=" + FOOD_CATEGORY + "&oauth_token=" + TOKEN + "&v=" + VERSION; 
	private static final String GET_VENUE = HOST + "/venues/${id}?oauth_token=" + TOKEN + "&v=" + VERSION;
	
	public static Venue get(String id) throws Exception {
		String queryString = StringUtils.replaceOnce(GET_VENUE, "${id}", id);
		
		try {
			// 4sq JSON
			JsonNode node = parse(queryString);
			JsonNode venueNode = node.get("response").get("venue");
			
			Venue venue = new Venue();
			venue.setIdFoursquare(venueNode.get("id").asText());
			venue.setName(venueNode.get("name").asText());
			if (venueNode.get("location") != null && 
					venueNode.get("location").get("address") != null) {
				venue.setAddress(venueNode.get("location").get("address").asText());
			}
			
			Category category = new Category();
//			category.setId(id);
			
			return venue;
		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}
	
	/**
	 * 
	 * @param city
	 * @param q
	 * @return
	 */
	public static List<Venue> search(String city, String q) {
		String queryString = StringUtils.replaceOnce(SEARCH_VENUE, "${near}", city);
		queryString = StringUtils.replaceOnce(queryString, "${q}", q);
		
		List<Venue> venues = new ArrayList<Venue>();
		
		// 4sq JSON
		JsonNode node = parse(queryString);
		JsonNode arr = node.get("response").get("venues");

		for (int i = 0; i < arr.size(); i++) {
			JsonNode v = arr.get(i);
			
			Venue venue = new Venue();
			
			venue.setIdFoursquare(v.get("id").asText());
			
			if (v.get("location") != null && 
					v.get("location").get("address") != null) {
				venue.setAddress(v.get("location").get("address").asText());
			}
			venue.setName(v.get("name").asText());
//			venue.set
			
			venues.add(venue);
		}
		
		return venues;
	}
	
	/**
	 * 
	 * @param queryString
	 * @return
	 */
	private static JsonNode parse(String queryString) {
		try {
			LOG.debug("Opening " + queryString + " ...");
			URL url = new URL(queryString);
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(url);
			
//			JSONObject jo = JSONObject.fromObject(IOUtils.toString(is, "UTF-8"));
//			is.close();
			return node;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
