package sb.quantocusta.api.temp;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public enum Status {
	
	VALID("V"),
	EXPIRED("E");
	
	private String code;
	
	private Status(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

}
