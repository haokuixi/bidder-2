package com.bharatmehta.bidder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@SpringBootApplication
@EnableAutoConfiguration
@EnableWebMvc
@Import({CacheConfiguration.class})
public class BidderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BidderApplication.class, args);
	}
	
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.bharatmehta.bidder.web"))
                .paths(PathSelectors.any())
                .build();
    }
}
