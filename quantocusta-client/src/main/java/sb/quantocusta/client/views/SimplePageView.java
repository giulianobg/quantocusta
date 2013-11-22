package sb.quantocusta.client.views;

import java.nio.charset.Charset;

import com.yammer.dropwizard.views.View;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class SimplePageView extends View {
	
	public SimplePageView(String path) {
		super(path, Charset.forName("UTF-8"));
	}
	
//	public SimplePageView(String path, Object anything) {
//		super(path);
//	}

}
