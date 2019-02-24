package com.cyeste.games.mastermind.adapters.api.rest.resources;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.cyeste.games.mastermind.domain.GuessResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("guess")
public class GuessResources implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("code")
	private String[] code;
	@JsonProperty("coloredPegs")
	private int coloredPegs;
	@JsonProperty("whitePegs")
	private int whitePegs;
	@JsonProperty("solved")
	private boolean solved;
	public GuessResources(String[] code, int coloredPegs, int whitePegs, boolean solved) {
		this.code = code;
		this.coloredPegs = coloredPegs;
		this.whitePegs = whitePegs;
		this.solved = solved;
	}
	
	public String[] getCode() {
		return code;
	}
	public int getColoredPegs() {
		return coloredPegs;
	}
	public int getWhitePegs() {
		return whitePegs;
	}
	public boolean isSolved() {
		return solved;
	}
	
	public static GuessResources toResource(GuessResult guessResult) {
		return new GuessResources(guessResult.getGuess().toStringArray(), guessResult.getColoredPegs(), guessResult.getWhitePegs(), guessResult.isExactMatch());
	}
	
	public static GuessResources[] toResources(Iterator<GuessResult> guesses) {
		List<GuessResources> resources = new LinkedList<GuessResources>();
		while(guesses.hasNext()) {
			resources.add(toResource(guesses.next()));
		}
		return resources.toArray(new GuessResources[] {});
	}
	
}
