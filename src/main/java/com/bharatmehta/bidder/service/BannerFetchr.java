package com.bharatmehta.bidder.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.bharatmehta.bidder.domain.Banner;
import com.bharatmehta.bidder.dto.AuthResponse;
import com.bharatmehta.bidder.dto.BannerDto;
import com.bharatmehta.bidder.dto.BannersDto;
import com.bharatmehta.bidder.repository.BannerRepository;

/**
 * 
 * Class to fetch the Banners from external API 
 * @author bharatmehta
 *
 */
@Component
public class BannerFetchr {

	private static final Logger LOGGER = LoggerFactory.getLogger(BannerFetchr.class);
	
	@Autowired
	private BannerRepository bannerRepository;
	
	@Value("${AUTH}")
	private  String authenticationRequest;
	
	@Value("${AUTHURL}")
	private String authURL;
	
	@Value("${BANNERURL}")
	private String bannerURL;
	
	@Value("${BANNERSURL}")
	private String bannersURL;
	
	
	private String authenticationToken;
	

	
	

	private String authenticationToken() {
		if(authenticationToken == null){
			authenticateToApi();
		}
		return authenticationToken;
	}
	
	
	@PostConstruct
	public void fetch() {
		LOGGER.info("Fetching Banners from {}" , bannersURL);
		
		ResponseEntity<AuthResponse> response = authenticateToApi();
		authenticationToken = response.getBody().getToken();
		
		    if(authenticationToken != null){
		    	ResponseEntity<BannersDto> banners = getBannersFromApi();
		    	if(banners != null){
		    		BannersDto bannersDto = banners.getBody();
		    		if(bannersDto != null){
		    			List<BannerDto> bannerDto = bannersDto.getBanners();
		    			
		    			if(bannerDto != null){
		    				for(BannerDto i : bannerDto){
		    					LOGGER.info("Inserting to database : {} ", i);
		    					Banner banner = new Banner(i.getId(),i.getSize().getHeight(),
		    							i.getSize().getWidth(),
		    							i.getBudget(),i.getBidPrice(), i.isActive(), bannerURL + i.getId());
		    					bannerRepository.saveAndFlush(banner);
		    				}
		    			}
		    		}
		    	}
		    	
		    }
		    
		    
		
	}


	private ResponseEntity<AuthResponse> authenticateToApi() {
		try{
			return restTemplate().exchange(authURL, HttpMethod.POST, entity(), AuthResponse.class);
		}catch(RestClientException e){
			LOGGER.error("Error occured while authenticating to  banners at {} ", authURL);
			throw e;
		}
		
	}


	private ResponseEntity<BannersDto> getBannersFromApi() {
		try{
			return restTemplate().exchange(bannersURL,HttpMethod.GET,entity(authheaders()), BannersDto.class);
		}catch(RestClientException e){
			LOGGER.error("Error occured while fetching banners from {} ", bannersURL);
			throw e;
		}
		
	}
	
	
	private RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	    return restTemplate;
	}
	
	private HttpEntity<String> entity(){
		HttpEntity<String> entity = new HttpEntity<String>(authenticationRequest, headers());
		return entity;
		
	}
	
	private HttpEntity<String> entity(HttpHeaders headers){
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return entity;
		
	}
	
	private HttpHeaders headers(){
		 HttpHeaders headers = new HttpHeaders();
		  headers.setContentType(MediaType.APPLICATION_JSON);
		  return headers;
	}
	
	private HttpHeaders authheaders(){
		 HttpHeaders headers = new HttpHeaders();
		 headers.add("Authorization", authenticationToken());
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 return headers;
	}
	
	

}
