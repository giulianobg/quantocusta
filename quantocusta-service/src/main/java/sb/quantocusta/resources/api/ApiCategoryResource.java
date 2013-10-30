package sb.quantocusta.resources.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.mongojack.JacksonDBCollection;

import sb.quantocusta.api.Category;
import sb.quantocusta.dao.CategoryDao;
import sb.quantocusta.dao.Daos;

import com.mongodb.DB;

/**
 * 
 * @author Giuliano Griffante
 */
@Path("/api/category")
@Produces("application/json; charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON)
public class ApiCategoryResource {
	
//	private DB db; 
	private JacksonDBCollection<Category, String> coll;

	public ApiCategoryResource(DB db) {
		coll = JacksonDBCollection.wrap(db.getCollection("testcat"), Category.class, String.class);
	}
	
	@POST
	@Path("thrd/{id}")
	public Object findBy3rdId(@PathParam("id") String id) {
		CategoryDao dao = Daos.get(CategoryDao.class);
		Category category = dao.findBy3rdId(id);
		
		if (category == null) {
			category = dao.insert(category);
		}
		
		return category;
	}

//	@POST
//	@Path("price")
//	public Object submitPrice(@QueryParam("id") String id,
//			@QueryParam("type") String type,
//			@QueryParam("v") String v) {
////		db.
//		return null;
//	}
	
	private Category create(Category category) {
		String savedId = coll.insert(category).getSavedId();
		return coll.findOneById(savedId);
	}

}
