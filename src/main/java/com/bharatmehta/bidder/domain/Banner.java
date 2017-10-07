package com.bharatmehta.bidder.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="banner")
public class Banner {
	
	@Id
	private String id;
	
	private double height;
	
	private double width ;
	
	private double budget;
	
	private double bidPrice;
	
	private boolean active;
	
	private String url;

	
	public Banner() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Banner(String id, double height, double width, double budget, double bidPrice, boolean active, String url) {
		super();
		this.id = id;
		this.height = height;
		this.width = width;
		this.budget = budget;
		this.bidPrice = bidPrice;
		this.active = active;
		this.url = url;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public double getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(double bidPrice) {
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

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(bidPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(budget);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		temp = Double.doubleToLongBits(width);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Banner)) {
			return false;
		}
		Banner other = (Banner) obj;
		if (active != other.active) {
			return false;
		}
		if (Double.doubleToLongBits(bidPrice) != Double.doubleToLongBits(other.bidPrice)) {
			return false;
		}
		if (Double.doubleToLongBits(budget) != Double.doubleToLongBits(other.budget)) {
			return false;
		}
		if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (url == null) {
			if (other.url != null) {
				return false;
			}
		} else if (!url.equals(other.url)) {
			return false;
		}
		if (Double.doubleToLongBits(width) != Double.doubleToLongBits(other.width)) {
			return false;
		}
		return true;
	}

	
	

	

}
