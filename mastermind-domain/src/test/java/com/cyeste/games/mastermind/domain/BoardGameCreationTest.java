package com.cyeste.games.mastermind.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.cyeste.games.mastermind.domain.exception.InvalidOperationException;

@RunWith(BlockJUnit4ClassRunner.class)
public class BoardGameCreationTest {

	private static final int DEFAULT_MAX_GAMES = 5;
	private static final List<Peg> DEFAULT_PEGS = new LinkedList<Peg>();
	private Pattern code;
	private DecodingBoard board;

	@BeforeClass
	public static void populateCodePegs() {
		DEFAULT_PEGS.add(new Peg(Peg.Color.BLUE));
		DEFAULT_PEGS.add(new Peg(Peg.Color.GREEN));
		DEFAULT_PEGS.add(new Peg(Peg.Color.GREEN));
		DEFAULT_PEGS.add(new Peg(Peg.Color.YELLOW));
		assertFalse(DEFAULT_PEGS.isEmpty());
		assertTrue(DEFAULT_PEGS.size() == 4);
		System.out.println(Arrays.toString(DEFAULT_PEGS.toArray()));
	}

	@Before
	public void setUp() {
		code = new Pattern(DEFAULT_PEGS);
		board = new DecodingBoard(DEFAULT_MAX_GAMES, code);
	}

	@Test
	public void initialState() {
		assertTrue(board.leftGames());
		assertFalse(board.isSolved());
	}

	@Test
	public void moreGamesAreAllowed() {
		// given a guess
		List<Peg> guessPegs = new LinkedList<Peg>(DEFAULT_PEGS);
		Collections.reverse(guessPegs);
		Pattern guess = new Pattern(guessPegs);

		// when guess for once
		board.guess(guess);

		// then
		assertTrue(board.leftGames());
	}

	@Test(expected = InvalidOperationException.class)
	public void noMoreGamesAllowed() {
		List<Peg> guessPegs = new LinkedList<Peg>(DEFAULT_PEGS);
		Collections.reverse(guessPegs);
		Pattern guess = new Pattern(guessPegs);
		for (int gameCount = 0; gameCount < DEFAULT_MAX_GAMES + 1; gameCount++) {
			board.guess(guess);
		}
	}

	@Test
	public void guessWithColorMatchingsAndPosotionsMatchings() {
		// given a guess
		List<Peg> guessPegs = new LinkedList<Peg>(DEFAULT_PEGS);
		Collections.reverse(guessPegs);
		Pattern guess = new Pattern(guessPegs);

		// when guess for once
		GuessResult guessResult = board.guess(guess);

		// then
		assertNotNull(guessResult);
		assertEquals(2, guessResult.getColoredPegs());
		assertEquals(2, guessResult.getWhitePegs());
		assertFalse(guessResult.isExactMatch());
		assertTrue(board.leftGames());
	}
}
