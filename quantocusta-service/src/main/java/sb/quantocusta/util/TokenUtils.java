package sb.quantocusta.util;

import java.math.BigInteger;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class TokenUtils {
	
	public static String tokenFromId(String id) {
		try {
			// gera um código a partir do id informado
			String token = new MD5Generator().generateValue(id);
			
			// tranforma em um BigInteger base 16
			BigInteger intToken = new BigInteger(token, 16);
			token = String.valueOf(intToken.flipBit(7)); // flip bit
			
			// gera outro código a partir do resultado atual
			token = new MD5Generator().generateValue(token);
			
			return token;
		} catch (OAuthSystemException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
