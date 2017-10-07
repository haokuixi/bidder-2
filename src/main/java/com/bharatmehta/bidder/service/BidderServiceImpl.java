package com.bharatmehta.bidder.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bharatmehta.bidder.domain.Banner;
import com.bharatmehta.bidder.domain.Bid;
import com.bharatmehta.bidder.repository.BannerRepository;
import com.bharatmehta.bidder.repository.BidRepository;

@Service
public class BidderServiceImpl implements BidderService {

	@Autowired
	private BannerRepository bannerRepository;
	
	@Autowired
	private BidRepository bidRepository;
	

	@Override
	@Transactional(readOnly = true)
	public Banner findActiveBanner( double height, double width) {
		Banner result = bannerRepository.findActiveByHeightAndWidth(height, width);
		return result;
	}



	@Override
	@Transactional(readOnly = false)
	public Bid bid(String tid, double height, double width) {
		Objects.nonNull(tid);
		
		Banner banner = findActiveBanner( height, width);
		Bid bid = new Bid();
		
		if(banner != null ){
			Double currentBidAmount = bidRepository.findSumByBanner(banner.getId());
			currentBidAmount = currentBidAmount == null ? 0.0 :currentBidAmount;
			double budget = banner.getBudget();
			if((budget - currentBidAmount) >= banner.getBidPrice()){
				bid.setBannerId(banner.getId());
				bid.setPrice(banner.getBidPrice());
				bid.setUrl(banner.getUrl());
			}
			
			
		}else{
			bid.setPrice(0.0);
		}
		bid.setTid(tid);
		bidRepository.saveAndFlush(bid);
		return bid;
	}

}
