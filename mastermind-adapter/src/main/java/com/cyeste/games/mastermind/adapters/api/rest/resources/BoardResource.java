package com.cyeste.games.mastermind.adapters.api.rest.resources;

import java.io.Serializable;

import com.cyeste.games.mastermind.domain.DecodingBoard;
import com.cyeste.games.mastermind.domain.Pattern;
import com.cyeste.games.mastermind.domain.PlayerBoard;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("board")
public class BoardResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("code")
	private String[] code;
	
	@JsonProperty("guesses")
	private GuessResources[] guesses;
	
	@JsonProperty("solved")
	private boolean isSolved;
	
	@JsonProperty("leftMoreGames")
	private boolean leftMoreGames;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("codeMaker")
	private PlayerResource codeMaker;
	
	@JsonProperty("gamesLeft")
	private int gamesLeft;
	
	
	@JsonCreator
	public BoardResource(
			@JsonProperty("id")String id,
			@JsonProperty("code")String[] code,
			@JsonProperty("guesses") GuessResources[] guesses, 
			@JsonProperty("solved") boolean isSolved, 
			@JsonProperty("leftMoreGames")boolean leftMoreGames, 			
			@JsonProperty("codeMaker") PlayerResource codeMaker,
			@JsonProperty("gamesLeft") int gamesLeft) {
		this.code = code;
		this.guesses = guesses;
		this.isSolved = isSolved;
		this.leftMoreGames = leftMoreGames;
		this.id = id;
		this.codeMaker = codeMaker;
		this.gamesLeft = gamesLeft;
	}

	public String[] getCode() {
		return code;
	}

	public GuessResources[] getGuesses() {
		return guesses;
	}

	public boolean isSolved() {
		return isSolved;
	}

	public boolean isLeftMoreGames() {
		return leftMoreGames;
	}

	public String getId() {
		return id;
	}

	public PlayerResource getCodeMaker() {
		return codeMaker;
	}
	
	public static BoardResource toResource(DecodingBoard board) {
		return new BoardResource(
				board.getId().toString(),
				new String[] {"*"}, 
				GuessResources.toResources(board.games()), 
				board.isSolved(), 
				board.leftGames(), null, board.gamesLeft());
	}
	
	public static BoardResource toResource(PlayerBoard playerBoard) {
		DecodingBoard board = playerBoard.getBoard();
		Pattern code = board.getCode();
		boolean isCodeMaker = playerBoard.isCoder();
		
		return  new BoardResource(
				board.getId().toString(), 
				(isCodeMaker) ? 
						code.toStringArray() : new String[] {"*"},
						GuessResources.toResources(board.games()), 
				board.isSolved(), 
				board.leftGames(), 
				PlayerResource.toResource(playerBoard.getPlayer()),board.gamesLeft());
	}

	
	
}
