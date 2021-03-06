package com.bharatmehta.bidder.dto;

import javax.validation.constraints.NotNull;

public class BidRequestDto {

	@NotNull(message = "tid is mandatory")
	private String tid;
	
	private String refer;
	
	private String wpos;
	
	private String wtype;
	
	private String ssp;
	
	private String adsize;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getRefer() {
		return refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public String getWpos() {
		return wpos;
	}

	public void setWpos(String wpos) {
		this.wpos = wpos;
	}

	public String getWtype() {
		return wtype;
	}

	public void setWtype(String wtype) {
		this.wtype = wtype;
	}

	public String getSsp() {
		return ssp;
	}

	public void setSsp(String ssp) {
		this.ssp = ssp;
	}

	public String getAdsize() {
		return adsize;
	}

	public void setAdsize(String adsize) {
		this.adsize = adsize;
	}
	
	public int getHeight(){
		
		int height = -1;
		try{
			String [] adSizeArray = adsize.split("x");
			height = Integer.parseInt(adSizeArray[0]);
		}catch(Exception e){
			//TODO
		}
		
		return height;
	}
	
	
	
	public int getWidth(){
		
		int width = -1;
		try{
			String [] adSizeArray = adsize.split("x");
			 width = Integer.parseInt(adSizeArray[1]);
		}catch(Exception e){
		//TODO	
		}
		
		return width;
	}
	
	
}
