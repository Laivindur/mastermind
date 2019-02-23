package com.cyeste.games.mastermind.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 
 * @author Christian Yeste Vidal
 *
 */
public class DecodingBoard {

	private final Pattern code;
	private final Collection<GuessResult> games;
	private boolean solved;
	
	public DecodingBoard(int maxGameSize, Pattern code) {
		this.code = code;
		this.games = new ArrayList<GuessResult>(maxGameSize);
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
	
	public Iterator<GuessResult> games(){
		return games.iterator();
	}
	
	public Pattern getCode() {
		return code;
	}
}
