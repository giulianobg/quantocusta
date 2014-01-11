package sb.quantocusta.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.mongodb.DBObject;

/**
 * 
 * @author Giuliano Griffante
 */
public class Valuation {

	private Integer count;
	private Double average;
	
	@JsonInclude(Include.NON_NULL)
	private DBObject me;
	
	public Valuation() {
		count = 0;
		average = 0.;
	}
	
	public Integer getCount() {
		return count;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Double getAverage() {
		return average;
	}
	
	public void setAverage(Double average) {
		this.average = average;
	}
	
	public DBObject getMe() {
		return me;
	}
	
	public void setMe(DBObject me) {
		this.me = me;
	}
	
}
