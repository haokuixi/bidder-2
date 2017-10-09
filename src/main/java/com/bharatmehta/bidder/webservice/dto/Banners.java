package com.bharatmehta.bidder.webservice.dto;

import java.util.ArrayList;
import java.util.List;

public class Banners {

	
	private List<BannerDto> bannerDtos = new ArrayList<BannerDto>();

	public List<BannerDto> getBanners() {
		return bannerDtos;
	}

	public void setBanners(List<BannerDto> bannerDtos) {
		this.bannerDtos = bannerDtos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Banners [bannerDtos=");
		builder.append(bannerDtos);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
