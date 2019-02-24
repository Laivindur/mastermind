package com.cyeste.games.mastermind.adapters.api.rest.command;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("input_codemaker")
public class PlayerCodeInput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonCreator
	public PlayerCodeInput(@JsonProperty("codeMakerId") final String codeMakerId, @JsonProperty("code") String[] code) {
		this.codeMakerId = codeMakerId;
		this.code = code;
	}

	@JsonProperty("codeMakerId")
	private String codeMakerId;
	
	@JsonProperty("code")
	private String[] code;
}
