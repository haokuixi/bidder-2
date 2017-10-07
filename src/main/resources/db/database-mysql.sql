CREATE DATABASE `bidder`;

CREATE TABLE `banner` (
  `id` varchar(200) NOT NULL,
  `bid_price` decimal(10,2) DEFAULT NULL,
  `budget` decimal(11,2) DEFAULT NULL,
  `height` decimal(10,2) DEFAULT NULL,
  `width` decimal(10,2) DEFAULT NULL,
  `active` varchar(45) DEFAULT NULL,
  `url` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Banners';


CREATE TABLE `bid` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tid` varchar(450) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `banner_id` varchar(50) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `url` varchar(450) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='Bids';
