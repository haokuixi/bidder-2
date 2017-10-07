package com.bharatmehta.bidder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableAutoConfiguration
@EnableWebMvc
@Import({CacheConfiguration.class})
public class BidderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BidderApplication.class, args);
	}
}
