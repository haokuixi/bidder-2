package com.bharatmehta.bidder.repository;

import java.math.BigDecimal;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bharatmehta.bidder.domain.Bid;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
	
	
	
	@Query(nativeQuery = true , value ="select sum(price) from bid where banner_id = ?")
	public BigDecimal findSumByBanner(String bannerId);

}