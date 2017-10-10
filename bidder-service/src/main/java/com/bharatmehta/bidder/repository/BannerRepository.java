package com.bharatmehta.bidder.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bharatmehta.bidder.domain.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, String> {
	
	@Cacheable(value = "banners")
	@Query(nativeQuery = true, value = "select a.* from banner a ,"
			+ "(select banner_id , max(created) as created from banner  group by banner_id) as b "
			+ "where a.banner_id = b.banner_id and a.created = b.created and a.active = 1 and a.height = ? and a.width = ? "
			+ "order by a.bid_price  desc limit 1")
	public Banner findActiveByHeightAndWidth(double heigt, double width);
	
	
	

}
