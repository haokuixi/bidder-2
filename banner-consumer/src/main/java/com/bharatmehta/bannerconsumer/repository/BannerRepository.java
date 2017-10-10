package com.bharatmehta.bannerconsumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bharatmehta.bannerconsumer.domain.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, String> {
	
	
	
	

}
