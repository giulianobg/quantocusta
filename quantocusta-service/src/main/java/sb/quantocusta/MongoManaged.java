package sb.quantocusta;

import com.mongodb.Mongo;
import com.yammer.dropwizard.lifecycle.Managed;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class MongoManaged implements Managed {

	private Mongo m;

	public MongoManaged(Mongo m) {
		this.m = m;
	}

	public void start() throws Exception {
	}

	public void stop() throws Exception {
		m.close();
	}
}