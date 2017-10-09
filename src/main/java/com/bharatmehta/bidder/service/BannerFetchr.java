package com.bharatmehta.bidder.service;

import java.text.MessageFormat;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.bharatmehta.bidder.domain.Banner;
import com.bharatmehta.bidder.exception.WebServiceAuthenticationException;
import com.bharatmehta.bidder.repository.BannerRepository;
import com.bharatmehta.bidder.webservice.dto.AuthResponse;
import com.bharatmehta.bidder.webservice.dto.BannerDto;
import com.bharatmehta.bidder.webservice.dto.Banners;

/**
 * 
 * Class to fetch the Banners from external API 
 * @author bharatmehta
 *
 */
@Component
public class BannerFetchr implements Fetchr {

	private static final Logger LOGGER = LoggerFactory.getLogger(BannerFetchr.class);
	
	@Autowired
	private BannerRepository bannerRepository;
	
	@Value("${application.webservice.authentication.request}")
	private  String authenticationRequest;
	
	@Value("${application.webservice.authentication}")
	private String authURL;
	
	@Value("${application.webservice.show-banner}")
	private String bannerURL;
	
	@Value("${application.webservice.banners}")
	private String bannersURL;
	
	
	private String authenticationToken;
	

	/* (non-Javadoc)
	 * @see com.bharatmehta.bidder.service.Fetchr#authenticate()
	 */
	@Override
	@Transactional(readOnly = true)
	public boolean authenticate() {
		boolean isAuthenticated = false;
		try{
			ResponseEntity<AuthResponse> response = restTemplate().exchange(authURL, HttpMethod.POST, entity(), AuthResponse.class);
			if(response.getStatusCode() == HttpStatus.OK ){
				LOGGER.info("Authentication successfull at {}",authURL);
			    AuthResponse tokenResponse = response.getBody();
			    if(tokenResponse != null){
			    	authenticationToken = tokenResponse.getToken(); 
			    	isAuthenticated = true;
			    }
				
			}else{
				
				LOGGER.error("Getting {} response from {} ",response.getStatusCode(), authURL);
				throw new WebServiceAuthenticationException("Response Code :" + response.getStatusCode());
			}
		}catch(RestClientException e){
			LOGGER.error("Problem occurred while authenticating",e);
			throw new WebServiceAuthenticationException(e);
		}
		return isAuthenticated;
	
	}
	
	

    /* (non-Javadoc)
	 * @see com.bharatmehta.bidder.service.Fetchr#fetch()
	 */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Scheduled(fixedDelay = 5000)
	public int fetch(){
		return fetchAndSaveBanners();
	}
	
  
    private  int fetchAndSaveBanners() {
	 int count = 0;
	 try{
		 
		 ResponseEntity<Banners> response =  restTemplate().exchange(bannersURL,HttpMethod.GET,entity(headers(true)), Banners.class);
		 if(response.getStatusCode() == HttpStatus.OK){
			
			  Banners bannersInResponse = response.getBody();
			  if(bannersInResponse != null){
				  List<BannerDto> banners = bannersInResponse.getBanners();
				  if(banners != null){
					  for(BannerDto i : banners){
						  Banner banner = new Banner(i.getId(),i.getSize().getHeight(), i.getSize().getWidth(),	i.getBudget(),i.getBidPrice(), i.isActive(),
								  bannerURL + i.getId());
						  bannerRepository.saveAndFlush(banner);
						  count += 1;
					  }
				  }
			  }
			  
		  }else if(response.getStatusCode() == HttpStatus.FORBIDDEN){
			  throw new BannersNotFoundException(new WebServiceAuthenticationException(MessageFormat.format("{0} responded with {1}", new Object[]{bannersURL, response.getStatusCode()})));
			 
			  
		  }else{
			  throw new BannersNotFoundException(MessageFormat.format("{0} responded with {1}", new Object[]{bannersURL, response.getStatusCode()}));
		  }
	 }catch(RestClientException e){
		 LOGGER.error("Problem occurred while fetching banners from {}",bannersURL);
		 throw new BannerInAccibleException(e);
	 }catch(Exception e){
		 LOGGER.error("Problem occurred while fetching banners from {}",bannersURL);
		 throw new BannerInAccibleException(e);
	 }
	  
	  return count;
   }
	
	@PostConstruct
	public void init() {
		if(authenticate()){
			fetchAndSaveBanners();
		}
		
	}


	
	
	private RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
	    return restTemplate;
	}
	
	private HttpEntity<String> entity(){
		HttpEntity<String> entity = new HttpEntity<String>(authenticationRequest, headers( false));
		return entity;
		
	}
	
	private HttpEntity<String> entity(HttpHeaders headers){
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return entity;
		
	}
	
	private HttpHeaders headers(boolean authenticate){
		 HttpHeaders headers = new HttpHeaders();
		 if(authenticate){
			 headers.add("Authorization", authenticationToken);
		 }
		  headers.setContentType(MediaType.APPLICATION_JSON);
		  return headers;
	}
	
	
	

}
