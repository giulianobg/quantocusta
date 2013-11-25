package sb.quantocusta.views;

import java.nio.charset.Charset;

import sb.quantocusta.api.User;

import com.yammer.dropwizard.views.View;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class SimplePageView extends View {
	
	public User me;
	
	public SimplePageView(String path, User me) {
		super(path, Charset.forName("UTF-8"));
		this.me = me;
	}
	
	public User getMe() {
		return me;
	}
	
	public void setMe(User me) {
		this.me = me;
	}
	
//	public SimplePageView(String path, Object anything) {
//		super(path);
//	}

}
