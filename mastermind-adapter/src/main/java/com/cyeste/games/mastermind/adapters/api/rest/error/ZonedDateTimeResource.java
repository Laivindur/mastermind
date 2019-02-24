package com.cyeste.games.mastermind.adapters.api.rest.error;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ZonedDateTimeResource implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ZonedDateTime dateTime;
	
	public ZonedDateTimeResource(ZonedDateTime dateTime) {
		this.dateTime = dateTime;
	}	

	@JsonProperty("date_time")	
	public ZonedDateTime getDateTime() {
		return dateTime;
	}
	
	public String getZone() {
		return dateTime.getZone().getId();
	}
}