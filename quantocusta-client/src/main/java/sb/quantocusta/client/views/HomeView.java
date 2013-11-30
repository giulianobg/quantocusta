package sb.quantocusta.client.views;

import sb.quantocusta.api.User;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class HomeView extends SimplePageView {
	
	public User user;
	
	public HomeView() {
		super("/assets/tpl/index.ftl");
	}
	
	public HomeView(User user) {
		super("/assets/tpl/home.ftl");
		this.user = user;
	}

}
