package sb.quantocusta.client.resources;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import sb.quantocusta.client.QuantoCustaClientConfiguration;
import sb.quantocusta.resources.BaseResouce;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import com.google.common.io.InputSupplier;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.multipart.FormDataParam;

@Path("upload")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.MULTIPART_FORM_DATA)
public class FileResource extends BaseResouce {

	private QuantoCustaClientConfiguration configuration;

	private Client client;
	private ObjectMapper mapper;

	public FileResource(QuantoCustaClientConfiguration configuration) {
		this.configuration = configuration;
		client = Client.create();
		mapper = new ObjectMapper();
	}

	@POST
	public String uploadFile(@FormDataParam("file") final InputStream stream) throws Exception {
		String tempname = UUID.randomUUID().toString();
		final String outputPath = configuration.getNfs() + "images" + File.separator + tempname;
		Files.copy(new InputSupplier<InputStream>() {
            public InputStream getInput() throws IOException {
                return stream;
            }
        }, new File(outputPath));

		return tempname;
	}

}
