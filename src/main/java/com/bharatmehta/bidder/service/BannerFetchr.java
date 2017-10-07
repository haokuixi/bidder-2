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
import com.bharatmehta.bidder.repository.BannerFetchrTest;
import com.bharatmehta.bidder.repository.BannerRepository;

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
	
	
	private String authenticationToken;
	

	
	

	public String authenticationToken() {
		if(authenticationToken == null){
			fetch();
		}
		return authenticationToken;
	}
	
	
	@PostConstruct
	public void fetch() {
			ResponseEntity<AuthResponse> response = restTemplate().exchange(authURL, HttpMethod.POST, entity(), AuthResponse.class);
		    authenticationToken = response.getBody().getToken();
		    if(authenticationToken != null){
		    	ResponseEntity<BannersDto> banners = restTemplate().exchange("http://localhost:8888/banners",HttpMethod.GET,
		    			entity(authheaders()), BannersDto.class);
		    	if(banners != null){
		    		BannersDto bannersDto = banners.getBody();
		    		if(bannersDto != null){
		    			List<BannerDto> bannerDto = bannersDto.getBanners();
		    			
		    			if(bannerDto != null){
		    				for(BannerDto i : bannerDto){
		    					System.out.println(i);
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
