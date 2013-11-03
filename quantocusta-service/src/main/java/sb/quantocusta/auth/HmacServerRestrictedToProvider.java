//package sb.quantocusta.auth;
//
//import org.apache.log4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.sun.jersey.api.model.Parameter;
//import com.sun.jersey.core.spi.component.ComponentContext;
//import com.sun.jersey.core.spi.component.ComponentScope;
//import com.sun.jersey.spi.inject.Injectable;
//import com.sun.jersey.spi.inject.InjectableProvider;
//
//public class HmacServerRestrictedToProvider<T> implements InjectableProvider<RestrictedTo, Parameter> {
//
//	static final Logger LOG = LoggerFactory.getLogger(HmacServerRestrictedToProvider.class);
//
//	private final Authenticator<HmacServerCredentials, T> authenticator;
//	private final String realm;
//
//	/**
//	 * Creates a new {@link HmacServerRestrictedToProvider} with the given {@link com.yammer.dropwizard.auth.Authenticator} and realm.
//	 *
//	 * @param authenticator the authenticator which will take the {@link HmacServerCredentials} and
//	 *                      convert them into instances of {@code T}
//	 * @param realm         the name of the authentication realm
//	 */
//	public HmacServerRestrictedToProvider(Authenticator<HmacServerCredentials, T> authenticator, String realm) {
//		this.authenticator = authenticator;
//		this.realm = realm;
//	}
//
//	@Override
//	public ComponentScope getScope() {
//		return ComponentScope.PerRequest;
//	}
//
//	@Override
//	public Injectable<?> getInjectable(ComponentContext ic,
//			RestrictedTo a,
//			Parameter c) {
//		return new HmacServerRestrictedToInjectable<T>(authenticator, realm, a.value());
//	}
//}