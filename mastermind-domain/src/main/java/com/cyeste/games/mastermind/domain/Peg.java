package com.cyeste.games.mastermind.domain;

/**
 * 
 * @author Christian Yeste Vidal
 *
 */
public class Peg {
	
	private final Color color;
	
	public Peg(Color color) {
		this.color = color;		
	}
	
	public boolean equals(Peg peg) {
		return peg != null && color.equals(peg.color);
	}

	public static enum Color {
		BLUE,RED,YELLOW,CYAN,GREEN,PURPLE,BROWN,PINK, BLANK
	}
}
