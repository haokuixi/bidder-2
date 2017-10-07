package com.bharatmehta.bidder.dto;


public class BannerDto {
	
	private String id;
	
	private SizeDto size;
	
	private double budget;
	
	private double bidPrice;
	
	private boolean active;
	

	
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


	public SizeDto getSize() {
		return size;
	}

	public void setSize(SizeDto size) {
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
