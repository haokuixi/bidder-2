package com.bharatmehta.bannerconsumer.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bharatmehta.bannerconsumer.aspect.TimeTrack;
import com.bharatmehta.bannerconsumer.domain.Banner;
import com.bharatmehta.bannerconsumer.exception.BannersNotFoundException;
import com.bharatmehta.bannerconsumer.repository.BannerRepository;
import com.bharatmehta.bannerconsumer.webservice.dto.BannerDto;
import com.bharatmehta.bannerconsumer.webservice.dto.Banners;

/**
 * @author Bharat.Mehta
 *
 */
@Service
public class BannerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BannerService.class);
	
	@Autowired
	private BannerFetchr bannerFetchr;
	
	@Autowired
	private BannerRepository repository;
	

	public List<Banner> pullBanners(){
		List<Banner> result = null;
		try{
			if(bannerFetchr.authenticate()){
				Banners banners = bannerFetchr.fetch();
				if(banners != null && !banners.getBanners().isEmpty()){
					result = pushToDataBase(banners);
				}
			}
		}catch(BannersNotFoundException e){
			if(e.getCause() != null){
				LOGGER.error(e.getCause().getMessage(),e);
			}else{
				LOGGER.error(e.getMessage(),e);
			}
			throw e;
		}
		return result;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW , readOnly = false)
	public void pushToDatabase(List<Banner> banner){
		if(banner != null & !banner.isEmpty())
			repository.save(banner);
	}
	
	
	@Scheduled(fixedDelay = 5000)
	@TimeTrack
	public void pullAndPushToDatabase(){
		pushToDatabase(pullBanners());
	}

	private List<Banner> pushToDataBase(Banners banners) {
		List<BannerDto> banner = banners.getBanners();
		List<Banner> objects = new ArrayList<Banner>();
		for(BannerDto i : banner){
			Banner obj = 
			new Banner(i.getId(),i.getSize().getHeight(),i.getSize().getWidth(),i.getBudget(),i.getBidPrice(),i.isActive(),bannerFetchr.getBannerURL()+i.getId());
			objects.add(obj);
		}
		return objects;
	}

}
