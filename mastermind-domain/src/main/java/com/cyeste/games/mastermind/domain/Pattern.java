package com.cyeste.games.mastermind.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.cyeste.games.mastermind.domain.Peg.Color;
import com.cyeste.games.mastermind.domain.utils.Validations;

/**
 * 
 * Componente de tipo <i>ValueObject</i> que representa un código.
 * Un código  es uan secuencia de colores. Notese que la longuitud del patrón no es relevante para el propio patrón. Esto es un aspecto 
 * de configuración o parametrización de la aplicación
 * @author Christian Yeste Vidal
 *
 */
public class Pattern {

	private final List<Peg> pegs;
	private static final String TO_STRING = "{pegs:%s}";

	private Pattern(List<Peg> pegs) {
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
	
	public Peg[] toPegArray() {
		return pegs.toArray(new Peg[] {});
	}

	public String[] toStringArray() {
		return pegs.stream().map(Peg::getColor).map(Color::name).toArray(String[]::new);
	}
	
	/**
	 * Ejecuta una comparación de pegs. Esta comparación de pegs cuenta cuantos pegs (elementos de la sequencia) hacen matching 
	 * por color y posición
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
	 * Ejecuta una comparación de pegs solo por color. Esta comparación de pegs cuenta cuantos pegs (elementos de la sequencia) hacen matching 
	 * por color. No cuenta repetidos y descuenta aquellos que hicieron  matching por posición.
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
	
	
	
	public static PatternBuilder builder() {
		return new PatternBuilder();
	}
	
	/**
	 * Los {@link Pattern} solo pueden ser construidos mediante este builder
	 * @author Christian Yeste Vidal
	 *
	 */
	public static class PatternBuilder{
		private List<Peg> pegs;
		
		PatternBuilder(){
			pegs = new LinkedList<Peg>();
		}
		
		public PatternBuilder addPeg(Peg peg) {
			Validations.whenNull(peg).throwIllegalArgumentException("Null Pegs can not be added to the pattern");
			pegs.add(peg);
			return this;
		}
		
		public PatternBuilder addPeg(Color color) {
			return addPeg(Peg.createPeg(color));			
		}
		
		public PatternBuilder addPeg(String color) {
			return addPeg(Peg.createPeg(color));
		}
		
		public Pattern build() {
			Validations.whenEmpty(pegs).throwIllegalArgumentException("Pattern's Peg set is required");
			return new Pattern(pegs);
		}
	}
}
