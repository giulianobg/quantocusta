package sb.quantocusta;

import java.security.SecureRandom;
import java.util.UUID;

public class Main {
	private static volatile SecureRandom numberGenerator = null;
    private static final long MSB = 0x8000000000000000L;
	
	public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
        System.out.println(Main.genRandom32Hex());

        System.out.println();
        System.out.println(Long.toHexString(0x8000000000000000L |21));
        System.out.println(Long.toBinaryString(0x8000000000000000L |21));
        System.out.println(Long.toHexString(Long.MAX_VALUE + 1));
    }
	
	public static String genRandom32Hex() {
        SecureRandom ng = numberGenerator;
        if (ng == null) {
            numberGenerator = ng = new SecureRandom();
        }

        return Long.toHexString(MSB | ng.nextLong()) + Long.toHexString(MSB | ng.nextLong());
    }

}
