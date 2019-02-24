package com.cyeste.games.mastermind.adapters.api.rest.command;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("input_codebreaker")
public class PlayerGuessImput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonCreator
	public PlayerGuessImput(
			@JsonProperty("codeBreakerId")final String codeBreakerId, 
			@JsonProperty("guess") final String[] guess, 
			@JsonProperty("boardId") final String boardId) {
		this.codeBreakerId = codeBreakerId;
		this.guess = guess;
		this.boardId = boardId;
	}

	@JsonProperty("codeBreakerId")
	private String codeBreakerId;

	@JsonProperty("guess")
	private String[] guess;

	@JsonProperty("boardId")
	private String boardId;
}
