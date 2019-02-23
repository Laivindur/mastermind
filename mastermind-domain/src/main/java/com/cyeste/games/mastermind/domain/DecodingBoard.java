package com.cyeste.games.mastermind.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.cyeste.games.mastermind.domain.utils.Validations;

/**
 * 
 * @author Christian Yeste Vidal
 *
 */
public class DecodingBoard {

	private final int maxGamesSize;
	private final Pattern code;
	private final Collection<GuessResult> games;
	private boolean solved;
	
	public DecodingBoard(int maxGameSize, Pattern code) {
		Validations.whenNull(code).throwIllegalArgumentException("DecodingBoard's code is required");
		Validations.when(maxGameSize <=0).throwIllegalArgumentException("DecodingBoard max games size must be greater than 0");
		this.code = code;
		this.games = new ArrayList<GuessResult>(maxGameSize);
		this.maxGamesSize = maxGameSize;
		solved = false;
	}
	
	public GuessResult guess(Pattern code) {
		GuessResult result =  new GuessResult(code.clone(), -1, -1);
		games.add(result);
		solved = false;
		return result;
	}
	
	public boolean isSolved() {
		return solved;
	}
	
	public boolean leftGames() {
		return maxGamesSize - (int)games.stream().count() > 0;
	}
	
	public Iterator<GuessResult> games(){
		return games.iterator();
	}
	
	public Pattern getCode() {
		return code;
	}
}
