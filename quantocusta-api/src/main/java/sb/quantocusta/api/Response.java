package sb.quantocusta.api;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class Response {
	
	private Integer status;
	
	private Long timestamp;
	
	private Object result;
	
	public Response() {
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
	
}
