package sb.quantocusta.client.views;

import java.nio.charset.Charset;

import com.yammer.dropwizard.views.View;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class ErrorView extends View {
	
	public ErrorView() {
		super("/assets/tpl/error.ftl", Charset.forName("UTF-8"));
	}
	
	public ErrorView(String path) {
		super(path);
		
		
		System.out.println(getCharset());
		System.out.println();
	}

}
