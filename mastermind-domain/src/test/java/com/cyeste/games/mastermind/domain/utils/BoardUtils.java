package com.cyeste.games.mastermind.domain.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.cyeste.games.mastermind.domain.DecodingBoard;
import com.cyeste.games.mastermind.domain.Pattern;
import com.cyeste.games.mastermind.domain.Peg;

public final class BoardUtils {
	
	private static final int DEFAULT_MAX_GAMES_PER_BOARD = 5;
	private BoardUtils() {
	}

	public static Pattern generateCode(Peg.Color... colors) {
		List<Peg> codePegs = new LinkedList<Peg>();
		Arrays.stream(colors).forEach(color -> codePegs.add(Peg.createPeg(color)));
		return new Pattern(codePegs);
	}
	
	public static Pattern generateCode(Peg... pegs) {
		List<Peg> codePegs = new LinkedList<Peg>();
		Arrays.stream(pegs).forEach(peg -> codePegs.add(peg));
		return new Pattern(codePegs);
	}
	
	public static Pattern generateCode(Iterator<Peg> pegs) {
		List<Peg> codePegs = new LinkedList<Peg>();
		while(pegs.hasNext()) {
			codePegs.add(pegs.next());
		}
		return new Pattern(codePegs);
	}
	
	public static DecodingBoard generateBoard(Peg... pegs) {
		return new DecodingBoard(DEFAULT_MAX_GAMES_PER_BOARD, generateCode(pegs));
	}
	
	public static DecodingBoard generateBoard(Peg.Color... colors) {
		return new DecodingBoard(DEFAULT_MAX_GAMES_PER_BOARD, generateCode(colors));
	}
}
