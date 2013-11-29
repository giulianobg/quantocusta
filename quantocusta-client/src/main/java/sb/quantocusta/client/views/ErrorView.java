package sb.quantocusta.client.views;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class ErrorView extends SimplePageView {
	
	public ErrorView() {
		super("/assets/tpl/error.ftl");
	}
	
	public ErrorView(String path) {
		super(path);
	}

}
