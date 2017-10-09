package com.bharatmehta.bidder.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bharatmehta.bidder.domain.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, String> {
	
	@Cacheable(value = "banners")
	@Query(nativeQuery = true, value = "select * from banner where height = ? and width = ? and (active = 1 ) order by bid_price desc limit 1")
	public Banner findActiveByHeightAndWidth(double heigt, double width);
	
	
	

}
