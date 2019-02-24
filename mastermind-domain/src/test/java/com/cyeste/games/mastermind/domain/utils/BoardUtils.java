package com.cyeste.games.mastermind.domain.utils;

import java.util.Arrays;
import java.util.Iterator;

import com.cyeste.games.mastermind.domain.DecodingBoard;
import com.cyeste.games.mastermind.domain.Pattern;
import com.cyeste.games.mastermind.domain.Pattern.PatternBuilder;
import com.cyeste.games.mastermind.domain.Peg;
import com.cyeste.games.mastermind.domain.Peg.Color;

public final class BoardUtils {
	
	private static final int DEFAULT_MAX_GAMES_PER_BOARD = 5;
	private BoardUtils() {
	}

	public static Pattern generateCode(Color... colors) {
		PatternBuilder builder = Pattern.builder();
		Arrays.stream(colors).forEach(color -> builder.addPeg(color));
		return builder.build();
	}
	
	
	
	public static Pattern generateCode(Iterator<Peg> pegs) {
		PatternBuilder builder = Pattern.builder();
		while(pegs.hasNext()) {
			builder.addPeg(pegs.next());
		}
		return builder.build();
	}
	
	
	public static DecodingBoard generateBoard(Color... colors) {
		return DecodingBoard.createBoard("id",DEFAULT_MAX_GAMES_PER_BOARD, generateCode(colors));
	}
}
