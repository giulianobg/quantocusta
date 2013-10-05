//package org.quantocusta.api;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.PrePersist;
//import javax.persistence.PreUpdate;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//
///**
// * The persistent class for the city database table.
// * 
// */
//@Entity
//public class City implements Serializable {
//	
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@SequenceGenerator(name="CITY_ID_GENERATOR", sequenceName="SQ_ID_CITY", allocationSize=1)
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CITY_ID_GENERATOR")
//	private Long id;
//
//	@ManyToOne
//	@JoinColumn(name="id_country")
//	private Country country;
//
//	private String name;
//
//	private String state;
//	
//	private String context;
//
//	private String status;
//	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name="created_at", updatable=false)
//	private Date createdAt;
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name="updated_at")
//	private Date updatedAt;
//
//	public City() {
//	}
//
//	public Long getId() {
//		return this.id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Country getCountry() {
//		return country;
//	}
//	
//	public void setCountry(Country country) {
//		this.country = country;
//	}
//
//	public String getName() {
//		return this.name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getState() {
//		return this.state;
//	}
//
//	public void setState(String state) {
//		this.state = state;
//	}
//	
//	public String getContext() {
//		return context;
//	}
//	
//	public void setContext(String context) {
//		this.context = context;
//	}
//
//	public String getStatus() {
//		return this.status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public Date getCreatedAt() {
//		return createdAt;
//	}
//	
//	public void setCreatedAt(Date createdAt) {
//		this.createdAt = createdAt;
//	}
//	
//	public Date getUpdatedAt() {
//		return updatedAt;
//	}
//	
//	public void setUpdatedAt(Date updatedAt) {
//		this.updatedAt = updatedAt;
//	}
//	
//	@PrePersist
//	protected void onCreate() {
//		setCreatedAt(new Date());
//		setUpdatedAt(getCreatedAt());
//	}
//
//	@PreUpdate
//	protected void onUpdate() {
//		setUpdatedAt(new Date());
//	}
//
//}