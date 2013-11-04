package sb.quantocusta.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author Giuliano Griffante
 */
public class Valuation {

	private Integer totalCount;
	private Integer smileCount;
	private Integer poutCount;
	private Double smileAverage;
	private Double poutAverage;
	
	@JsonInclude(Include.NON_NULL)
	private Vote me;
	
	public Valuation() {
		totalCount = 0;
		smileCount = 0;
		poutCount = 0;
		smileAverage = 0.;
		poutAverage = 0.;
	}
	
//	public String getKind() {
//		return kind;
//	}
//	
//	public void setKind(String kind) {
//		this.kind = kind;
//	}
	
	public Integer getPoutCount() {
		return poutCount;
	}
	
	public void setPoutCount(Integer poutCount) {
		this.poutCount = poutCount;
	}
	
	public Integer getSmileCount() {
		return smileCount;
	}
	
	public void setSmileCount(Integer smileCount) {
		this.smileCount = smileCount;
	}
	
	public Integer getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	
	public Double getPoutAverage() {
		return poutAverage;
	}
	
	public void setPoutAverage(Double poutAverage) {
		this.poutAverage = poutAverage;
	}
	
	public Double getSmileAverage() {
		return smileAverage;
	}
	
	public void setSmileAverage(Double smileAverage) {
		this.smileAverage = smileAverage;
	}
	
	public Vote getMe() {
		return me;
	}
	
	public void setMe(Vote me) {
		this.me = me;
	}
	
}
