package sb.quantocusta.api;

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
	
	private Integer status;
	
	private Long timestamp;
	
	private Object result;
	
	public DataResponse() {
		timestamp = System.currentTimeMillis();
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	public Object getResult() {
		return result;
	}
	
	public void setResult(Object result) {
		this.result = result;
	}
	
	public static DataResponse build(int code) {
		DataResponse r = new DataResponse();
		r.setStatus(code);
		return r;
	}
	
	public static DataResponse build(int code, Object result) {
		DataResponse r = build(code);
		r.setResult(result);
		return r;
	}
	
}
