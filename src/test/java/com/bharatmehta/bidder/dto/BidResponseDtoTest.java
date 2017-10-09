package com.bharatmehta.bidder.dto;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.bharatmehta.bidder.domain.Bid;

public class BidResponseDtoTest {

	private BidResponseDto dto ;
	
	
	@Before
	public void setup(){
		dto = new BidResponseDto();
	}
	
	
	@Test
	public void testGetBids() {
		assertNotNull(dto.getBids());
		assertTrue(dto.getBids().isEmpty());
	}

	@Test
	public void testAdd() {
		
		for(int i = 0 ; i <10 ; i++){
			 dto.add(bid());
		}
		
		assertEquals(dto.getBids().size(),10);
		for(int i = 0; i <10; i++){
			assertEquals("123.123",dto.getBids().get(i).getPrice());
			assertEquals("ABC1",dto.getBids().get(i).getTid());
			assertEquals("URL",dto.getBids().get(i).getUrl());
		}
		 
		  
	}


	private Bid bid() {
		Bid bid = new Bid();
		  bid.setBannerId("ABC");
		  bid.setPrice(new BigDecimal("123.123"));
		  bid.setTid("ABC1");
		  bid.setUrl("URL");
		  bid.setCreated(new Date());
		  return bid;
	}

}
