/**
 * 
 */
package com.bharatmehta.bidder.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.bharatmehta.bidder.domain.Banner;

/**
 * @author Bharat.Mehta
 *
 */
public class BannerBudgetPredicateTest {

	
	private BannerBudgetPredicate predicate ;
	
	@Before
	public void setup(){
		predicate = new BannerBudgetPredicate();
	}
	/**
	 * Test method for {@link com.bharatmehta.bidder.service.BannerBudgetPredicate#test(java.lang.Double, com.bharatmehta.bidder.domain.Banner)}.
	 */
	@Test
	public void testTest() {
		Banner banner = new Banner("1", 1, 1, "123.12", "0.12", true, "1");
	    BigDecimal amountInBids = BigDecimal.ZERO;
	    
	    assertTrue(predicate.test(amountInBids, banner));
	    while(predicate.test(amountInBids, banner)){
	    	amountInBids = amountInBids.add(banner.getBidPrice());
	    	System.out.println(amountInBids);
	    }
	    
	    
	    assertFalse(predicate.test(amountInBids, banner));
				
	}

}
