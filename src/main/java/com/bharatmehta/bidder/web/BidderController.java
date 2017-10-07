package com.bharatmehta.bidder.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bharatmehta.bidder.domain.Bid;
import com.bharatmehta.bidder.dto.BidRequestDto;
import com.bharatmehta.bidder.dto.BidResponseDto;
import com.bharatmehta.bidder.service.BidderService;

@RestController
@RequestMapping(value = "/bidder")
public class BidderController {


	@Autowired
	private BidderService bidderService;
	
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody BidResponseDto bid(@Valid BidRequestDto bidRequest){
	
		Bid bid = bidderService.bid(bidRequest.getTid(), bidRequest.getHeight(), bidRequest.getWidth());
		BidResponseDto response = new BidResponseDto();
		response.add(bid);
		return response;
		
		
	}
	
	
}
