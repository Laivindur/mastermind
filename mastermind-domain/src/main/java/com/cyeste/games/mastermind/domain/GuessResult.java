package com.cyeste.games.mastermind.domain;

/**
 * 
 * @author Christian Yeste Vidal
 *
 */
public class GuessResult {

	private final Pattern guess;
	private int whitePegs;
	private int coloredPeg;
	
	public GuessResult(Pattern guess, int exactMatchings, int colorsMatchings) {
		this.guess = guess.clone();
		this.whitePegs = colorsMatchings;
		this.coloredPeg = exactMatchings;
	}

	public boolean isExactMatch() {
		return coloredPeg == guess.length();
	}

	public Pattern getGuess() {
		return guess;
	}

	public int getColoredPegs() {
		return coloredPeg;
	}

	public int getWhitePegs() {
		return whitePegs;
	}
}
