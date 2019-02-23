package com.cyeste.games.mastermind.domain;

import java.util.LinkedList;
import java.util.List;

import com.cyeste.games.mastermind.domain.utils.Validations;

/**
 * 
 * @author Christian Yeste Vidal
 *
 */
public class Pattern {

	private final List<Peg> pegs;
	
	public Pattern(List<Peg> pegs) {
		Validations.whenEmpty(pegs).throwIllegalArgumentException("Pattern's Peg set is required");
		this.pegs = new LinkedList<Peg>(pegs);
	}
	
	public Pattern clone() {
		return new Pattern(new LinkedList<Peg>(pegs));
	}

	public int length() {
		return pegs.size();
	}
	
	public boolean hasPeg(Peg peg) {
		return pegs.contains(peg);
	}
	
	public int matchIngPositoins(Pattern pattern) {
		int matchings = 0;
		for(int position = 0; position < pegs.size(); position ++) {
			matchings += (pegs.get(position).equals(pattern.pegs.get(position))?1:0);
		}
		return matchings;
	}
}
