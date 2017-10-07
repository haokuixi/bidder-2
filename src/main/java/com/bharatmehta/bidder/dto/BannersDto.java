package com.bharatmehta.bidder.dto;

import java.util.ArrayList;
import java.util.List;

public class BannersDto {

	
	private List<BannerDto> banners = new ArrayList<BannerDto>();

	public List<BannerDto> getBanners() {
		return banners;
	}

	public void setBanners(List<BannerDto> banners) {
		this.banners = banners;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BannersDto [banners=");
		builder.append(banners);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
