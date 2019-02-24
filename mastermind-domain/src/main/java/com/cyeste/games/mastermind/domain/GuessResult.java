package com.cyeste.games.mastermind.domain;

/**
 * Componente de tipo <i> ValueObject</i> que representa una intento de descifrar el código de un tablero-
 * @author Christian Yeste Vidal
 *
 */
public class GuessResult {

	/**
	 * Sequencia de colores propuesta
	 */
	private final Pattern guess;
	/**
	 * Número de aproximaciones por color
	 */
	private int whitePegs;
	/**
	 * Aciertos por color y posición
	 */
	private int coloredPeg;
	
	GuessResult(Pattern guess, int exactMatchings, int colorsMatchings) {
		this.guess = guess.clone();
		this.whitePegs = colorsMatchings;
		this.coloredPeg = exactMatchings;
	}

	/**
	 * Es un matching exacto si el total de {@code coloredPegs} es igual a la longuitud del patrón (código) propuesto
	 * @return
	 */
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
