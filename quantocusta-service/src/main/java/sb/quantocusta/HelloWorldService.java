//package sb.quantocusta;
//
//import org.skife.jdbi.v2.DBI;
//
//import sb.quantocusta.health.DatabaseHealthCheck;
//import sb.quantocusta.health.TemplateHealthCheck;
//import sb.quantocusta.resources.HelloWorldResource;
//import sb.quantocusta.resources.HomeResource;
//
//import com.yammer.dropwizard.Service;
//import com.yammer.dropwizard.assets.AssetsBundle;
//import com.yammer.dropwizard.config.Bootstrap;
//import com.yammer.dropwizard.config.Environment;
//import com.yammer.dropwizard.jdbi.DBIFactory;
//import com.yammer.dropwizard.jdbi.bundles.DBIExceptionsBundle;
//import com.yammer.dropwizard.views.ViewBundle;
//
//public class HelloWorldService extends Service<Configuration> {
//	
//    public static void main(String[] args) throws Exception {
//        new HelloWorldService().run(args);
//    }
//
//    @Override
//    public void initialize(Bootstrap<Configuration> bootstrap) {
//        bootstrap.setName("configuration");
//        bootstrap.addBundle(new ViewBundle());
//        bootstrap.addBundle(new DBIExceptionsBundle());
//        bootstrap.addBundle(new AssetsBundle("/assets"));
////        bootstrap.addBundle(new AssetsBundle("/css", "/assets/css"));
////        bootstrap.addBundle(new AssetsBundle("/font", "font"));
////        bootstrap.addBundle(new AssetsBundle("/js", "js"));
////        bootstrap.addBundle(new AssetsBundle("/assets2", "/src/main/resources/assets"));
////        bootstrap.addBundle(new AssetsBundle("/assets3", "/resources/assets"));
//    }
//
//    @Override
//    public void run(Configuration configuration,
//                    Environment environment) {
//        final String template = configuration.getTemplate();
//        final String defaultName = configuration.getDefaultName();
//        
//        final DBIFactory factory = new DBIFactory();
//        try {
//			final DBI jdbi = factory.build(environment, configuration.getDatabase(), "h2");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        
//        environment.addResource(new HelloWorldResource(template, defaultName));
//        environment.addResource(new HomeResource(template, defaultName));
//        
//        environment.addHealthCheck(new TemplateHealthCheck(template));
//        environment.addHealthCheck(new DatabaseHealthCheck(null));
//    }
//
//}
