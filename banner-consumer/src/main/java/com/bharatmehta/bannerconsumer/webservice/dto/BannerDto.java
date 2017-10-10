package com.bharatmehta.bannerconsumer.webservice.dto;


public class BannerDto {
	
	private String id;
	
	private Size size;
	
	private String budget;
	
	private String bidPrice;
	
	private boolean active;
	

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(String bidPrice) {
		this.bidPrice = bidPrice;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}


	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BannerDto [id=");
		builder.append(id);
		builder.append(", size=");
		builder.append(size);
		builder.append(", budget=");
		builder.append(budget);
		builder.append(", bidPrice=");
		builder.append(bidPrice);
		builder.append(", active=");
		builder.append(active);
		builder.append("]");
		return builder.toString();
	}

	
	

}
