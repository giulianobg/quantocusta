package sb.quantocusta.resources;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import sb.quantocusta.api.DataResponse;
import sb.quantocusta.api.client.Client;
import sb.quantocusta.dao.ClientDao;
import sb.quantocusta.dao.Daos;

@Path("oauth")
public class OAuthResource extends BaseResouce {

	@GET
	public Response oauth(@QueryParam("client_id") String clientId,
			@QueryParam("client_secret") String clientSecret,
			@QueryParam("redirect_uri") String redirectUri,
			@QueryParam("type") String type) {
		// check appId and appSecret
//		ClientDao dao = Daos.get(ClientDao.class);
//		Client client = dao.find(clientId, clientSecret);
		
//		if (client == null) {
//			Response.status(Status.NOT_FOUND).entity(DataResponse.build(Status.NOT_FOUND)).build();
//		}
		
		// generate code
		try {
			String code = new MD5Generator().generateValue();
			
			return Response.temporaryRedirect(URI.create(redirectUri + "?code=" + code)).build();
		} catch (OAuthSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// join with client appId and appSecret
//		client
		
		// return code
		
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@GET
	@Path("oauth_token")
	public Response oauthToken() {
		// check appId and appSecret
		
		// generate code
		
		// join with client appId and appSecret
		
		// return code
		
		return Response.status(Status.NO_CONTENT).build();
	}
	
	
	
	

}
