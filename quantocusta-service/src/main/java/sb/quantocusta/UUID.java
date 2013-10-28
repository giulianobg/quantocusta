package sb.quantocusta;

import java.security.SecureRandom;

public class UUID {
	
	private static SecureRandom numberGenerator = null;
	
	public static String genRandom32Hex() {
		long MSB = 0x8000000000000000L;
				
        if (numberGenerator == null) {
            numberGenerator = new SecureRandom();
        }

        return Long.toHexString(MSB | numberGenerator.nextLong()) + Long.toHexString(MSB | numberGenerator.nextLong());
    }

}
