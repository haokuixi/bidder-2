package com.bharatmehta.bidder.dto;

import java.util.ArrayList;
import java.util.List;

import com.bharatmehta.bidder.domain.Bid;


public class BidResponseDto {

	
	
	private List<BidDto> bids = new ArrayList<BidDto>();

	public List<BidDto> getBids() {
		return bids;
	}
	
	
	public void add(Bid ...bid){
		
		for(Bid i : bid){
			bids.add(new BidDto(i.getPrice(),i.getTid(),i.getUrl()));
		}
	}


	
	

}
