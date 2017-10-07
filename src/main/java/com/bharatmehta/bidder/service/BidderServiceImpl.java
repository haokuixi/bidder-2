package com.bharatmehta.bidder.service;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bharatmehta.bidder.domain.Banner;
import com.bharatmehta.bidder.domain.Bid;
import com.bharatmehta.bidder.exception.BidderServiceException;
import com.bharatmehta.bidder.repository.BannerRepository;
import com.bharatmehta.bidder.repository.BidRepository;

@Service
public class BidderServiceImpl implements BidderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BidderServiceImpl.class);
	
	@Autowired
	private BannerRepository bannerRepository;
	
	@Autowired
	private BidRepository bidRepository;
	

	private Banner findActiveBanner( double height, double width) {
		Banner result = bannerRepository.findActiveByHeightAndWidth(height, width);
		return result;
	}



	@Override
	@Transactional(readOnly = false)
	public Bid bid(String tid, double height, double width) throws BidderServiceException {
		Objects.nonNull(tid);
		Bid bid = new Bid();
		try{
			Banner banner = findActiveBanner( height, width);
			
			
			if(banner != null ){
				LOGGER.info("Banner:{} serves tid:{} ", banner.getId(),tid);
				
				Double currentBidAmount = bidRepository.findSumByBanner(banner.getId());
				currentBidAmount = currentBidAmount == null ? 0.0 :currentBidAmount;
				double budget = banner.getBudget();
				if((budget - currentBidAmount) >= banner.getBidPrice()){
					bid.setBannerId(banner.getId());
					bid.setPrice(banner.getBidPrice());
					bid.setUrl(banner.getUrl());
				}else{
					LOGGER.info("Budget of Banner:{} doesn't allow for tid:{}",banner.getId() , tid);
				}
				
				
			}else{
				LOGGER.info("No Banner found for tid {} ", tid);
			}
			
			
		}catch(Exception e ){
			LOGGER.error("Error while serving tid:{}" , tid);
			throw new BidderServiceException(e);
		}finally{
			bid.setTid(tid);
			bidRepository.saveAndFlush(bid);
		}
		return bid;
	}

}
