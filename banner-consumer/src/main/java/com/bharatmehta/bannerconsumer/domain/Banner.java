package com.bharatmehta.bannerconsumer.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="banner")
public class Banner {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="banner_id")
	private String bannerId;
	
	private int height;
	
	private int width ;
	
	private BigDecimal budget;
	
	private BigDecimal bidPrice;
	
	private boolean active;
	
	private String url;
	

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	


	
	public Banner() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Banner(String bannerId, int height, int width, String budget, String bidPrice, boolean active, String url) {
		super();
		this.bannerId = bannerId;
		this.height = height;
		this.width = width;
		this.budget = new BigDecimal(budget);
		this.bidPrice = new BigDecimal(bidPrice);
		this.active = active;
		this.url = url;
	}






	public String getBannerId() {
		return bannerId;
	}



	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public BigDecimal getBudget() {
		return budget;
	}



	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}



	public BigDecimal getBidPrice() {
		return bidPrice;
	}



	public void setBidPrice(BigDecimal bidPrice) {
		this.bidPrice = bidPrice;
	}



	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	
	
   public Date getCreated() {
		return created;
	}



	public void setCreated(Date created) {
		this.created = created;
	}





	public Integer getId() {
		return id;
	}



	
	
	

	

}
