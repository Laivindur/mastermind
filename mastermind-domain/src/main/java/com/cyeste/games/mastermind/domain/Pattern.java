package com.cyeste.games.mastermind.domain;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 
 * @author Christian Yeste Vidal
 *
 */
public class Pattern {

	private final Set<Peg> pegs;
	
	public Pattern(Set<Peg> pegs) {
		this.pegs = new LinkedHashSet<Peg>(pegs);
	}
	
	public Pattern clone() {
		return new Pattern(new LinkedHashSet<Peg>(pegs));
	}

	public int length() {
		return pegs.size();
	}
}
