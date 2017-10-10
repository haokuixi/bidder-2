/**
 * 
 */
package com.bharatmehta.bidder.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

/**
 * @author bharatmehta
 *
 */
@Entity
@Table(name="bid")
public class Bid {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String bannerId;
	
	private BigDecimal price;
	
	private String tid;
	
	private String url;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	
	
	

	public String getBannerId() {
		return bannerId;
	}



	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public BigDecimal getPrice() {
		return price  == null ? BigDecimal.ZERO : price;
	}



	public void setPrice(BigDecimal price) {
		this.price = price;
	}



	public String getTid() {
		return tid;
	}



	public void setTid(String tid) {
		this.tid = tid;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public Date getCreated() {
		return created;
	}



	public void setCreated(Date created) {
		this.created = created;
	}



	

}
