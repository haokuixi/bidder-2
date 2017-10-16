package com.bharatmehta.bidder.service;

import java.math.BigDecimal;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bharatmehta.bidder.aspect.TimeTrack;
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
	

	



	@Override
	@Transactional(readOnly = false)
	@TimeTrack
	public Bid bid(String tid, int height, int width) throws BidderServiceException {
		Objects.nonNull(tid);
		Bid bid = new Bid();
		try{
			Banner banner = findActiveBanner( height, width);
			
			
			if(banner != null ){
				LOGGER.info("Banner:{} serves tid:{} ", banner.getBannerId(),tid);
				
				BigDecimal currentBidAmount = bidRepository.findSumByBanner(banner.getBannerId());
				if(new BannerBudgetPredicate().test(currentBidAmount, banner)){
					bid.setBannerId(banner.getBannerId());
					bid.setPrice(banner.getBidPrice());
					bid.setUrl(banner.getUrl());
				}else{
					LOGGER.info("Budget of Banner:{} doesn't allow for tid:{}",banner.getBannerId() , tid);
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
	
	private Banner findActiveBanner( double height, double width) {
		Banner result = bannerRepository.findActiveByHeightAndWidth(height, width);
		return result;
	}

}
