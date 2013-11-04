package sb.quantocusta.api;

import javax.ws.rs.core.Response.Status;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class DataResponse {
	
	public static final int SUCCESS = 200;
	
	public static final int FORBIDDEN = 403;
	public static final int NOT_FOUND = 404;
	
	public static final int INTERNAL_ERROR = 500;
	
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
