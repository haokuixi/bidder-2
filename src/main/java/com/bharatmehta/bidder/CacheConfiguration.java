package com.bharatmehta.bidder;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Bharat.Mehta
 *
 */
@Configuration
@EnableCaching
@EnableScheduling
public class CacheConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(CacheConfiguration.class);
	
	@Bean
	public CacheManager cacheManager() {
		return  new ConcurrentMapCacheManager("banners");
	}

	
	@CacheEvict(allEntries = true, value = {"banners"})
	@Scheduled(fixedRate = 5000)
	public void reportCacheEvict() {
		
		LOGGER.info("Evicting cache: {} at {}", cacheManager().getCacheNames(),new Date());
	}
}