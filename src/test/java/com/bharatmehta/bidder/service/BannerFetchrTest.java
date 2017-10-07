
package com.bharatmehta.bidder.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bharatmehta.bidder.repository.BannerRepository;
import com.bharatmehta.bidder.service.BannerFetchr;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BannerFetchrTest {

	
	@Autowired
	private BannerFetchr fetchr;
	
	@Autowired
	private BannerRepository bannerRepository;
	
	
	@Test
	public void testFetch() {
		
		fetchr.fetch();
		assertNotNull(bannerRepository.findAll());
		
		
	}

}
