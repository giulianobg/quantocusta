package sb.quantocusta.client.views;

import com.yammer.dropwizard.views.View;

public class HomeView extends View {
	
	public HomeView() {
		super("/assets/tpl/index.ftl");
	}
	
	public HomeView(String path) {
		super(path);
		
//		System.out.println(get);
		
		System.out.println("HomeView.enclosing_method()");
		
		System.out.println(getCharset());
		System.out.println();
	}

}
