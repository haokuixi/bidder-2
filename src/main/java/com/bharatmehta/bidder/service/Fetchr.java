package com.bharatmehta.bidder.service;

import org.springframework.transaction.annotation.Transactional;

public interface Fetchr {

	boolean authenticate();

	int fetch();

}