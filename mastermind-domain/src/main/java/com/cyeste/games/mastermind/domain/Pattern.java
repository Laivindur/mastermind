package com.cyeste.games.mastermind.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import com.cyeste.games.mastermind.domain.utils.Validations;

/**
 * 
 * @author Christian Yeste Vidal
 *
 */
public class Pattern {

	private final Set<Peg> pegs;
	
	public Pattern(Set<Peg> pegs) {
		Validations.whenEmpty(pegs).throwIllegalArgumentException("Pattern's Peg set is required");
		this.pegs = new LinkedHashSet<Peg>(pegs);
	}
	
	public Pattern clone() {
		return new Pattern(new LinkedHashSet<Peg>(pegs));
	}

	public int length() {
		return pegs.size();
	}
}
