package com.bharatmehta.bidder.service;

import com.bharatmehta.bidder.domain.Banner;
import com.bharatmehta.bidder.domain.Bid;

public interface BidderService {

	
	public Banner findActiveBanner(double height, double width);
	
	public Bid bid(String tid, double height, double width);
}
