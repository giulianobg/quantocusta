package sb.quantocusta.views;

import com.yammer.dropwizard.views.View;

public class HomeView extends View {
	
	public HomeView() {
		super("prototype-home.html");
		
		System.out.println("HomeView.enclosing_method()");
		
		System.out.println(getCharset());
		System.out.println();
	}

}
