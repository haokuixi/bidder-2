package com.bharatmehta.bidder.repository;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidRepositoryTest {
	
	@Autowired
	private BidRepository repository;

	@Test
	public void test() {
		BigDecimal value = repository.findSumByBanner("1");
		System.out.println(value);
	}

}
