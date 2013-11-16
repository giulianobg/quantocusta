package sb.quantocusta.auth;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;
import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.api.model.Parameter;
import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.server.impl.inject.AbstractHttpContextInjectable;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;
import com.yammer.dropwizard.auth.Auth;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;

/**
 * A Jersey provider for OAuth2 bearer tokens.
 *
 * @param <T> the principal type
 */
public class QcAuthProvider<T> implements InjectableProvider<Auth, Parameter> {
	
    private static class QcAuthInjectable<T> extends AbstractHttpContextInjectable<T> {
        private static final Logger LOG = LoggerFactory.getLogger(QcAuthProvider.class);
//        private static final String HEADER_NAME = "WWW-Authenticate";
//        private static final String HEADER_VALUE = "Bearer realm=\"%s\"";
//        private static final String PREFIX = "bearer";

        private final Authenticator<String, T> authenticator;
        private final String realm;
        private final boolean required;

        private QcAuthInjectable(Authenticator<String, T> authenticator, String realm, boolean required) {
            this.authenticator = authenticator;
            this.realm = realm;
            this.required = required;
        }

        @Override
        public T getValue(HttpContext c) {
        	String accessToken = c.getRequest().getQueryParameters().getFirst("access_token");
        	
        	if (accessToken == null) {
        		throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("access_token is required to access this resource.")
                        .type(MediaType.TEXT_PLAIN_TYPE)
                        .build());
        	}
        	
        	// Verifica se accessToken está ativo
        	// se sim, retorna
			try {
				Optional<T> result = authenticator.authenticate(accessToken);
				
				if (result.isPresent()) {
					return result.get();
				} else {
					throw new WebApplicationException(Response.status(Status.ACCEPTED).
							entity("You have no access to this resource or your access_token was expired.").
							type(MediaType.TEXT_PLAIN).
							build());
				}
			} catch (AuthenticationException e) {
				LOG.warn(e.getMessage(), e);
				throw new WebApplicationException(e);
			}
        	
        	
//            try {
//                final String header = c.getRequest().getHeaderValue(HttpHeaders.AUTHORIZATION);
//                if (header != null) {
//                    final int space = header.indexOf(' ');
//                    if (space > 0) {
//                        final String method = header.substring(0, space);
//                        if (PREFIX.equalsIgnoreCase(method)) {
//                            final String credentials = header.substring(space + 1);
//                            final Optional<T> result = authenticator.authenticate(credentials);
//                            if (result.isPresent()) {
//                                return result.get();
//                            }
//                        }
//                    }
//                }
//            } catch (AuthenticationException e) {
//                LOGGER.warn("Error authenticating credentials", e);
//                throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
//            }

//            if (required) {
//                throw new WebApplicationException(Response.status(Response.Status.UNAUTHORIZED)
//                                                          .header(HEADER_NAME,
//                                                                  String.format(HEADER_VALUE,
//                                                                                realm))
//                                                          .entity("Credentials are required to access this resource.")
//                                                          .type(MediaType.TEXT_PLAIN_TYPE)
//                                                          .build());
//            }
        }
    }

    private final Authenticator<String, T> authenticator;
    private final String realm;

    /**
     * Creates a new OAuthProvider with the given {@link Authenticator} and realm.
     *
     * @param authenticator    the authenticator which will take the OAuth2 bearer token and convert
     *                         them into instances of {@code T}
     * @param realm            the name of the authentication realm
     */
    public QcAuthProvider(Authenticator<String, T> authenticator, String realm) {
        this.authenticator = authenticator;
        this.realm = realm;
    }

    public ComponentScope getScope() {
        return ComponentScope.PerRequest;
    }

    public Injectable<?> getInjectable(ComponentContext ic,
                                       Auth a,
                                       Parameter c) {
        return new QcAuthInjectable<T>(authenticator, realm, a.required());
    }
}
