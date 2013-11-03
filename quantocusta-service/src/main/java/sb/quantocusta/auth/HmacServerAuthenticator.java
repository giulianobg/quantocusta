//package sb.quantocusta.auth;
//
//import sb.quantocusta.api.User;
//import sb.quantocusta.dao.UserDao;
//
//import com.google.common.base.Optional;
//import com.yammer.dropwizard.auth.AuthenticationException;
//
//@Component
//public class HmacServerAuthenticator implements Authenticator<HmacServerCredentials, User> {
//
//	@Resource(name="hibernateUserDao")
//	private UserDao userDao;
//
//	@Override
//	public Optional<User> authenticate(HmacServerCredentials credentials) throws AuthenticationException {
//
//		// Get the User referred to by the API key
//		Optional<User> user = userDao.getByApiKey(credentials.getApiKey());
//		if (!user.isPresent()) {
//			return Optional.absent();
//		}
//
//		// Check that their authorities match their credentials
//		if (!user.get().hasAllAuthorities(credentials.getRequiredAuthorities())) {
//			return Optional.absent();
//		}
//
//		// Locate their secret key
//		String secretKey = user.get().getSecretKey();
//
//		String computedSignature = new String(
//				HmacUtils.computeSignature(
//						credentials.getAlgorithm(),
//						credentials.getCanonicalRepresentation().getBytes(),
//						secretKey.getBytes()));
//
//		// Avoid timing attacks by verifying every byte every time
//		if (isEqual(computedSignature.getBytes(), credentials.getDigest().getBytes())) {
//			return user;
//		}
//
//		return Optional.absent();
//
//	}
//
//	/**
//	 * Performs a byte array comparison with a constant time
//	 *
//	 * @param a A byte array
//	 * @param b Another byte array
//	 * @return True if the byte array have equal contents
//	 */
//	public static boolean isEqual(byte[] a, byte[] b) {
//		if (a.length != b.length) {
//			return false;
//		}
//
//		int result = 0;
//		for (int i = 0; i < a.length; i++) {
//			result |= a[i] ^ b[i];
//		}
//		return result == 0;
//	}
//
//}