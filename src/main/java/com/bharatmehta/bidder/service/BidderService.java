package com.bharatmehta.bidder.service;

import com.bharatmehta.bidder.domain.Bid;
import com.bharatmehta.bidder.exception.BidderServiceException;

public interface BidderService {

	
	
	public Bid bid(String tid, double height, double width) throws BidderServiceException;
}
