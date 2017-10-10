package com.bharatmehta.bannerconsumer.service;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.bharatmehta.bannerconsumer.exception.BannersNotFoundException;
import com.bharatmehta.bannerconsumer.exception.WebServiceAuthenticationException;
import com.bharatmehta.bannerconsumer.webservice.dto.AuthResponse;
import com.bharatmehta.bannerconsumer.webservice.dto.Banners;

/**
 * 
 * Class to fetch the Banners from external API 
 * @author bharatmehta
 *
 */
@Component
public class BannerFetchr  {

	private static final Logger LOGGER = LoggerFactory.getLogger(BannerFetchr.class);
	
	
	@Value("${application.webservice.authentication.request}")
	private  String authenticationRequest;
	
	@Value("${application.webservice.authentication}")
	private String authURL;
	
	@Value("${application.webservice.show-banner}")
	private String bannerURL;
	
	@Value("${application.webservice.banners}")
	private String bannersURL;
	
	
	private  String authenticationToken;
	

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
	
	

 
	public Banners fetch(){
		Banners  banner  = null;
		 try{
			 LOGGER.info("Getting banners from {}",bannersURL);
			 ResponseEntity<Banners> response =  restTemplate().exchange(bannersURL,HttpMethod.GET,entity(headers(true)), Banners.class);
			 if(response.getStatusCode() == HttpStatus.OK){
				
				  Banners bannersInResponse = response.getBody();
				  if(bannersInResponse != null){
					 banner = bannersInResponse;
				  }
				  
			  }else if(response.getStatusCode() == HttpStatus.FORBIDDEN){
				  throw new BannersNotFoundException(new WebServiceAuthenticationException(MessageFormat.format("{0} responded with {1}", new Object[]{bannersURL, response.getStatusCode()})));
				  
			  }else{
				  throw new BannersNotFoundException(MessageFormat.format("{0} responded with {1}", new Object[]{bannersURL, response.getStatusCode()}));
			  }
		 }catch(RestClientException e){
			 LOGGER.error("Problem occurred while fetching banners from {}",bannersURL);
			 throw new BannersNotFoundException(e);
		 }catch(Exception e){
			 LOGGER.error("Problem occurred while fetching banners from {}",bannersURL);
			 throw new BannersNotFoundException(e);
		 }
		  
		  return banner;
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




	public String getAuthURL() {
		return authURL;
	}




	public String getBannerURL() {
		return bannerURL;
	}




	public String getBannersURL() {
		return bannersURL;
	}
	
	
	

}
