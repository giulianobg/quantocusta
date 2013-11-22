package sb.quantocusta.resources.thirdy;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.OmniResponse;

/**
 * 
 * @author giuliano.griffante
 *
 */
public class GeoIP2Api {
	
	private static GeoIP2Api instance;
	private static DatabaseReader reader;
	
	public static void main(String[] args) {
		test();
	}

	public GeoIP2Api() {
		// A File object pointing to your GeoIP2 or GeoLite2 database
		File database = new File("/nfs/quantocusta/geoip2db/GeoLite2-City.mmdb");

		try {
			// This creates the DatabaseReader object, which should be reused across
			// lookups.
			reader = new DatabaseReader.Builder(database).build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static GeoIP2Api getInstance() {
		if (instance == null) {
			return instance = new GeoIP2Api();
		}
		return instance;
	}
	
	public static void test() {
		WebServiceClient client = new WebServiceClient.Builder(83212, "eVW1EX2Z91Vd").build();
		
		try {
			// Replace "omni" with the method corresponding to the web service that
			// you are using, e.g., "country", "cityIspOrg", "city".
			OmniResponse response = client.omni(InetAddress.getByName("128.101.101.101"));

			System.out.println(response.getCountry().getIsoCode()); // 'US'
			System.out.println(response.getCountry().getName()); // 'United States'
			System.out.println(response.getCountry().getNames().get("zh-CN")); // '美国'
	
			System.out.println(response.getMostSpecificSubdivision().getName()); // 'Minnesota'
			System.out.println(response.getMostSpecificSubdivision().getIsoCode()); // 'MN'
	
			System.out.println(response.getCity().getName()); // 'Minneapolis'
	
			System.out.println(response.getPostal().getCode()); // '55455'
	
			System.out.println(response.getLocation().getLatitude()); // 44.9733
			System.out.println(response.getLocation().getLongitude()); // -93.2323
		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GeoIp2Exception e) {
			e.printStackTrace();
		}
	}
	
	public String[] guessLatLng(String ip) { 
		try {
			// Replace "city" with the appropriate method for your database, e.g.,
			// "country".
			CityResponse response = reader.city(InetAddress.getByName(ip));

//			System.out.println(response.getCountry().getIsoCode()); // 'US'
//			System.out.println(response.getCountry().getName()); // 'United States'
//
//			System.out.println(response.getMostSpecificSubdivision().getName()); // 'Minnesota'
//			System.out.println(response.getMostSpecificSubdivision().getIsoCode()); // 'MN'
//
//			System.out.println(response.getCity().getName()); // 'Minneapolis'
//
//			System.out.println(response.getPostal().getCode()); // '55455'

//			System.out.println(response.getLocation().getLatitude()); // 44.9733
//			System.out.println(response.getLocation().getLongitude()); // -93.2323
			
			return new String[] {
					String.valueOf(response.getLocation().getLatitude()),
					String.valueOf(response.getLocation().getLongitude())
			};
		} catch (UnknownHostException e) {
//			e.printStackTrace();
		} catch (IOException e) {
//			e.printStackTrace();
		} catch (GeoIp2Exception e) {
//			e.printStackTrace();
		}
		
		return null;
	}

}
