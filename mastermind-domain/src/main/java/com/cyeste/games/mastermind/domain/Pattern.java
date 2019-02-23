package com.cyeste.games.mastermind.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.cyeste.games.mastermind.domain.utils.Validations;

/**
 * 
 * @author Christian Yeste Vidal
 *
 */
public class Pattern {

	private final List<Peg> pegs;
	private static final String TO_STRING = "{pegs:%s}";

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
	
	public Peg[] toArray() {
		return pegs.toArray(new Peg[] {});
	}

	/**
	 * Matching pegs per position and color
	 * @param pattern
	 * @return
	 */
	public int matchingPegs(Pattern pattern) {
		int matchings = 0;
		for (int position = 0; position < pegs.size(); position++) {
			matchings += (pegs.get(position).equals(pattern.pegs.get(position)) ? 1 : 0);
		}
		return matchings;
	}

	/**
	 * Matching pegs' colors
	 * @param pattern
	 * @return
	 */
	public int matchingPegColors(Pattern pattern) {
		Set<Peg> matchingPegs = new HashSet<Peg>();
		List<Peg> copyPegs = new LinkedList<Peg>(pegs);
		
		for (int position = 0; position < pegs.size(); position++) {
			Peg inputPatternPeg = pattern.pegs.get(position);
			//si hace matching con color
			if(copyPegs.contains(inputPatternPeg)) {
				//Lo cuento
				matchingPegs.add(inputPatternPeg);
			}
			//si hace matching con posición
			if(pegs.get(position).equals(inputPatternPeg)) {
				//lo descuento
				matchingPegs.remove(inputPatternPeg);
				copyPegs.remove(inputPatternPeg);
			}
		}
		return matchingPegs.size();
	}

	@Override
	public String toString() {
		return String.format(TO_STRING, Arrays.toString(pegs.toArray()));
	}
}
