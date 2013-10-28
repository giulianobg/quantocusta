package sb.quantocusta.views;

import com.yammer.dropwizard.views.View;

public class SearchView extends View {
	
	public SearchView() {
		super("/src/main/resources/home.ftl");
	}
	
	public SearchView(String path) {
		super(path);
		
//		System.out.println(get);
		
		System.out.println("HomeView.enclosing_method()");
		
		System.out.println(getCharset());
		System.out.println();
	}

}
