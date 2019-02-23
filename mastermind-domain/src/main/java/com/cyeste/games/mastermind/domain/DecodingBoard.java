package com.cyeste.games.mastermind.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import com.cyeste.games.mastermind.domain.exception.InvalidOperationException;
import com.cyeste.games.mastermind.domain.utils.Validations;

/**
 * 
 * @author Christian Yeste Vidal
 *
 */
public class DecodingBoard {

	private static final String TO_STRING = "{maxGamesSize : %d, code: %s, games: %s, solved: %s}";
	private final int maxGamesSize;
	private final Pattern code;
	private final Collection<GuessResult> games;
	private boolean solved;

	public DecodingBoard(int maxGameSize, Pattern code) {
		Validations.whenNull(code).throwIllegalArgumentException("DecodingBoard's code is required");
		Validations.when(maxGameSize <= 0).throwIllegalArgumentException("DecodingBoard max games size must be greater than 0");
		this.code = code;
		this.games = new ArrayList<GuessResult>(maxGameSize);
		this.maxGamesSize = maxGameSize;
		solved = false;
	}

	public GuessResult guess(Pattern guess) {
		if (leftGames()) {
			GuessResult result = new GuessResult(code.clone(), code.matchingPegs(guess), code.matchingPegColors(guess));
			solved = result.isExactMatch();
			games.add(result);
			return result;
		}
		throw new InvalidOperationException("The decoding boards is finished. No more guess are allowed");
	}

	public boolean isSolved() {
		return solved;
	}

	public boolean leftGames() {
		return maxGamesSize - (int) games.stream().count() > 0 && !isSolved();
	}

	public Iterator<GuessResult> games() {
		return games.iterator();
	}

	public Pattern getCode() {
		return code;
	}

	@Override
	public String toString() {
		return String.format(TO_STRING, maxGamesSize, code, Arrays.deepToString(games.toArray()), String.valueOf(solved));
	}
}
