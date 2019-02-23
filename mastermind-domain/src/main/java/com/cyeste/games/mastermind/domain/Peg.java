package com.cyeste.games.mastermind.domain;

import com.cyeste.games.mastermind.domain.utils.Validations;

/**
 * 
 * @author Christian Yeste Vidal
 *
 */
public class Peg {
	
	private final Color color;
	private static final String TO_STRING = "{color: \"%s\"}";
	
	Peg(Color color) {
		Validations.whenNull(color).throwIllegalArgumentException("Peg's color is required");
		this.color = color;		
	}
	
	public boolean equals(Object peg) {
		//The comparable is not null
		return peg != null && 
				//is instance of the same class
				Peg.class.isInstance(peg) &&
				//both are the same instance or both has the same color
				(this == peg || color.equals(Peg.class.cast(peg).color));
	}
	
	public Color getColor() {
		return color;
	}
	
	public static Peg createPeg(Color color) {
		return new Peg(color);
	}
	
	public static Peg createPeg(String colorCode) {
		Color color = Color.valueOf(colorCode);
		Validations.whenNull(color).throwIllegalArgumentException("Color "+ color + " is not supported");
		return new Peg(color);
		
	}

	/*
	 *For us to use Hashed data structures such as LinkedHashSet<> or LinkedHashMap<> 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return String.format(TO_STRING, color.name());
	}

	public static enum Color {
		BLUE,RED,YELLOW,CYAN,GREEN,PURPLE,BROWN,PINK, BLANK
	}
}
