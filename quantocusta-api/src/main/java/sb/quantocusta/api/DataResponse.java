package sb.quantocusta.api;

import javax.ws.rs.core.Response.Status;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class DataResponse {
	
	private Integer code;
	private String type;
	private String message;
	
//	private Long timestamp;
	
	private Object result;
	
	public DataResponse() {
//		timestamp = System.currentTimeMillis();
	}
	
	public Integer getCode() {
		return code;
	}
	
	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Object getResult() {
		return result;
	}
	
	public void setResult(Object result) {
		this.result = result;
	}
	
	public static DataResponse build(Status status) {
		return build(status.getStatusCode());
	}
	
	public static DataResponse build(int code) {
		DataResponse r = new DataResponse();
		r.setCode(code);
		
		if (code == Status.NOT_FOUND.getStatusCode()) {
			r.setMessage("Register not found.");
		} else if (code == Status.FORBIDDEN.getStatusCode()) {
			r.setMessage("You have no access to this register.");
		} else if (code == Status.OK.getStatusCode()) {
			r.setMessage("Everthing is OK! ;)");
		} else if (code == Status.INTERNAL_SERVER_ERROR.getStatusCode()) {
			r.setMessage("Error.");
			
		}
		
		r.setType(String.valueOf(r.getCode()));
		
		return r;
	}
	
	public static DataResponse build(Object result) {
		return build(Status.OK.getStatusCode(), result);
	}
	
	public static DataResponse build(int code, Object result) {
		DataResponse r = build(code);
		r.setResult(result);
		return r;
	}
	
}
