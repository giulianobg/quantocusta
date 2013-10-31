package sb.quantocusta.api;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class Review {
	
	private Integer idUser;
	private Double price;
	private long createdAt;
	
	public Integer getIdUser() {
		return idUser;
	}
	
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public long getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

}
