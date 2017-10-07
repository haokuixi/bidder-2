package com.bharatmehta.bidder;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bharatmehta.bidder.service.BidderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidderServiceTest {
	
	@Autowired
	private BidderService bidderService;

	@Test
	public void testOne() {
		assertNotNull(bidderService.findActiveBanner( 1.0, 1.0));
	}

}
