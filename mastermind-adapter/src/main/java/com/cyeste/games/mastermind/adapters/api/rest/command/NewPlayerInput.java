package com.cyeste.games.mastermind.adapters.api.rest.command;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewPlayerInput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	@NotEmpty
	@JsonProperty("name")
	private String name;
	

	@JsonCreator
	public NewPlayerInput(
			@JsonProperty("name")final String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
