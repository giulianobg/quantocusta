package sb.quantocusta.dao;

import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;

import sb.quantocusta.api.client.Client;

import com.mongodb.DB;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class ClientDao extends Dao<Client> {

	public ClientDao(DB db) {
		super(JacksonDBCollection.wrap(db.getCollection("clients"), Client.class, String.class));
	}
	
	public Client find(String clientId, String clientSecret) {
		return coll.findOne(DBQuery.is("_id", clientId).is("secret", clientSecret));
	}

}
