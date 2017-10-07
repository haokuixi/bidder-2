package com.bharatmehta.bidder.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class BidDto {

	private double price;
	
	private String tid;
	
	private String url;
	
	
	public BidDto() {
		// TODO Auto-generated constructor stub
	}

	
	

	public BidDto(double price, String tid, String url) {
		super();
		this.price = price;
		this.tid = tid;
		this.url = url;
	}




	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getTid() {
		return tid;
	}


	public void setTid(String tid) {
		this.tid = tid;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
	
	

}
