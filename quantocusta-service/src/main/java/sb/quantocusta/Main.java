package sb.quantocusta;

import sb.quantocusta.util.TokenUtils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class Main {
	
	public static void main(String[] args) {
			Cache<String, String> c1 = CacheBuilder.newBuilder().build();
//			CacheBuilder<K, V>
			
			System.out.println(TokenUtils.tokenFromId("5273c512da0649276addabd2"));
//			c1.put("token1", new MD5Generator().generateValue());
			
//			System.out.println(new OAuthIssuerImpl(new UUIDValueGenerator()).authorizationCode());
//			System.out.println(new OAuthIssuerImpl(new MD5Generator()).authorizationCode());
			
//			System.out.println(new UUIDValueGenerator().generateValue());
//			System.out.println(new UUIDValueGenerator().generateValue("dfkdk"));
			
			
//			System.out.println(Md5Crypt.md5Crypt("giuliano".getBytes()));
//			System.out.println(Md5Crypt.md5Crypt("giuliano".getBytes()));
//			System.out.println(Md5Crypt.md5Crypt("bernardes".getBytes()));
//			System.out.println(Md5Crypt.md5Crypt("griffante".getBytes()));
//			System.out.println(UUID.randomUUID());
			
//			174f93c3a563214dd805d19a9bfbd89c
//			e52c9c8d3d1f7643449af84100d5f5c6
//			VUSY3KHJITDBLXIFBRVPOD0YCFHIFO4NWOBGPIDW5D0STSYW
			
			
			
			
//		DB db = null;
//		try {
//			MongoClient client = new MongoClient("68.169.54.122", 27017);
//			db = client.getDB("quantocusta-dsv");
//			
//			Venue v = new Venue();
//			v.setAddress("Pça XV, s/n");
////			v.setAveragePrice(12.0);
//			v.setName("Café do Mercado");
//			
//			
////			City city = new City();
////			city.setName("Porto Alegre");
////			v.setCity(city);
//			
////			JacksonDBCollection<Category, String> collCat = JacksonDBCollection.wrap(db.getCollection("testcat"), Category.class, String.class);
////			
//////			Category category = new Category();
//////			category.setName("Café");
////			
////			Category category = collCat.findOneById("52710facda06bf1e6e670d0e");
////			
////			System.out.println(category);
////			
////			
////			
////			v.setCategory(category);
////			
////			JacksonDBCollection<Venue, String> coll = JacksonDBCollection.wrap(db.getCollection("test"), Venue.class, String.class);
//////			System.out.println(coll.insert(v));
////			
////			JacksonDBCollection<Vote, String> collVotes = JacksonDBCollection.wrap(db.getCollection("testvote"), Vote.class, String.class);
////			
////			Venue vv = coll.findOneById("527110b5da064650a70c9c18");
////			System.out.println(vv);
////			System.out.println(vv.getCategory());
////			
////			List<Venue> venues = coll.find(DBQuery.is("category._id", "52710facda06bf1e6e670d0e")).toArray();
////			System.out.println(venues.size());
//			
//			
////			User u = new User();
//////			u.setId(id);
////			u.setThirdyId("100000086507482");
////			u.setName("Giuliano Griffante");
//					
//			JacksonDBCollection<User, String> collUsers = JacksonDBCollection.wrap(db.getCollection("users"), User.class, String.class);
////			System.out.println(collUsers.insert(u).getSavedObject());
//			
//			System.out.println(collUsers.find(DBQuery.is("idUser", "5273c512da0649276addabd2").is("idVenue", "5272866ada0666d53f3c88f9")));
//			
//			client.close();
			
			
//			Optional<Integer> value = Optional.fromNullable(99);
//			System.out.println(value.or(0));
			
		
    }

}
