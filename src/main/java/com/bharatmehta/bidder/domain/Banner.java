package com.bharatmehta.bidder.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="banner")
public class Banner {
	
	@Id
	private String id;
	
	private int height;
	
	private int width ;
	
	private BigDecimal budget;
	
	private BigDecimal bidPrice;
	
	private boolean active;
	
	private String url;

	
	public Banner() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Banner(String id, int height, int width, String budget, String bidPrice, boolean active, String url) {
		super();
		this.id = id;
		this.height = height;
		this.width = width;
		this.budget = new BigDecimal(budget);
		this.bidPrice = new BigDecimal(bidPrice);
		this.active = active;
		this.url = url;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
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



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((bidPrice == null) ? 0 : bidPrice.hashCode());
		result = prime * result + ((budget == null) ? 0 : budget.hashCode());
		result = prime * result + height;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + width;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Banner other = (Banner) obj;
		if (active != other.active)
			return false;
		if (bidPrice == null) {
			if (other.bidPrice != null)
				return false;
		} else if (!bidPrice.equals(other.bidPrice))
			return false;
		if (budget == null) {
			if (other.budget != null)
				return false;
		} else if (!budget.equals(other.budget))
			return false;
		if (height != other.height)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (width != other.width)
			return false;
		return true;
	}

	
	

	

}
