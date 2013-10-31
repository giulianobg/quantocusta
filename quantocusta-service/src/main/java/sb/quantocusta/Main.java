package sb.quantocusta;

import java.net.UnknownHostException;
import java.util.List;

import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;

import sb.quantocusta.api.Category;
import sb.quantocusta.api.City;
import sb.quantocusta.api.Venue;
import sb.quantocusta.api.Vote;

import com.google.common.base.Optional;
import com.mongodb.DB;
import com.mongodb.MongoClient;

public class Main {
	
	public static void main(String[] args) {
        
		DB db = null;
		try {
			MongoClient client = new MongoClient("68.169.54.122", 27017);
			db = client.getDB("quantocusta-dev");
			
			Venue v = new Venue();
			v.setAddress("Pça XV, s/n");
//			v.setAveragePrice(12.0);
			v.setName("Café do Mercado");
			
			
			City city = new City();
			city.setName("Porto Alegre");
			v.setCity(city);
			
			JacksonDBCollection<Category, String> collCat = JacksonDBCollection.wrap(db.getCollection("testcat"), Category.class, String.class);
			
//			Category category = new Category();
//			category.setName("Café");
			
			Category category = collCat.findOneById("52710facda06bf1e6e670d0e");
			
			System.out.println(category);
			
			
			
			v.setCategory(category);
			
			JacksonDBCollection<Venue, String> coll = JacksonDBCollection.wrap(db.getCollection("test"), Venue.class, String.class);
//			System.out.println(coll.insert(v));
			
			JacksonDBCollection<Vote, String> collVotes = JacksonDBCollection.wrap(db.getCollection("testvote"), Vote.class, String.class);
			
			Venue vv = coll.findOneById("527110b5da064650a70c9c18");
			System.out.println(vv);
			System.out.println(vv.getCategory());
			
			List<Venue> venues = coll.find(DBQuery.is("category._id", "52710facda06bf1e6e670d0e")).toArray();
			System.out.println(venues.size());
			
			
			client.close();
			
			
			Optional<Integer> value = Optional.fromNullable(99);
			System.out.println(value.or(0));
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
    }

}
