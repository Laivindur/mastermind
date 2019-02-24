package com.cyeste.games.mastermind.adapters.api.rest.command;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("input_codemaker")
public class PlayerCodeInput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	@NotNull
	@JsonProperty("codeMakerId")
	private String codeMakerId;
	
	@NotEmpty
	@NotNull
	@JsonProperty("code")
	private String[] code;
	
	@JsonCreator
	public PlayerCodeInput(@JsonProperty("codeMakerId") final String codeMakerId, @JsonProperty("code") String[] code) {
		this.codeMakerId = codeMakerId;
		this.code = code;
	}

	public String[] getCode() {
		return code;
	}
	
	public String getCodeMakerId() {
		return codeMakerId;
	}
}
