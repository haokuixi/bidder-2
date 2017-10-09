package com.bharatmehta.bidder.service;

import java.math.BigDecimal;
import java.util.function.BiPredicate;

import com.bharatmehta.bidder.domain.Banner;

/**
 * @author Bharat.Mehta
 *
 */
public class BannerBudgetPredicate implements BiPredicate<BigDecimal, Banner> {

	@Override
	public boolean test(BigDecimal amountSpent, Banner banner) {
		if(amountSpent == null){
		  amountSpent = BigDecimal.ZERO;
		}
		BigDecimal amountSpentValue = amountSpent;
		BigDecimal bidPrice = banner.getBidPrice();
		BigDecimal budget = banner.getBudget();
		
		int result = budget.subtract(amountSpentValue.add(bidPrice)).compareTo(BigDecimal.ZERO);
			
		if (result >=0 ){
			return true;
		}
		return false;
		
	}

}
