package com.cyeste.games.mastermind.domain;

import static com.cyeste.games.mastermind.domain.utils.BoardUtils.generateCode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.cyeste.games.mastermind.domain.Peg.Color;
import com.cyeste.games.mastermind.domain.exception.BoardClosedException;
@RunWith(BlockJUnit4ClassRunner.class)
public class DecodingBoardGuessTest {

	private final static Logger LOGGER = Logger.getLogger(DecodingBoardGuessTest.class.getName());
	
	private static final int DEFAULT_MAX_GAMES = 5;
	private static final Pattern DEFAULT_CODE = generateCode(Peg.Color.BLUE, Peg.Color.GREEN, Peg.Color.GREEN,Peg.Color.YELLOW);
	private DecodingBoard board;
	
	@Before
	public void setUp() {
		board = DecodingBoard.createBoard("id",DEFAULT_MAX_GAMES, DEFAULT_CODE);
	}
	
	

	@Test
	public void initialState() {
		assertTrue(board.leftGames());
		assertFalse(board.isSolved());
	}

	@Test
	public void moreGamesAreAllowed() {
		// given a guess
		List<Peg> guessPegs = Arrays.asList(DEFAULT_CODE.toPegArray());
		Collections.reverse(guessPegs);
		Pattern guess = generateCode(guessPegs.iterator());

		// when guess for once
		board.guess(guess);

		// then
		assertTrue(board.leftGames());
		LOGGER.info("Board: " + board.toString());
	}

	@Test(expected = BoardClosedException.class)
	public void noMoreGamesAllowed() {
		List<Peg> guessPegs = Arrays.asList(DEFAULT_CODE.toPegArray());
		Collections.reverse(guessPegs);
		Pattern guess = generateCode(guessPegs.iterator());

		for (int gameCount = 0; gameCount < DEFAULT_MAX_GAMES + 1; gameCount++) {
			board.guess(guess);
			LOGGER.info("Board: " + board.toString());
		}
	}

	@Test
	public void guessWithColorMatchingsAndPosotionsMatchings() {
		// given a guess
		List<Peg> guessPegs = Arrays.asList(DEFAULT_CODE.toPegArray());
		Collections.reverse(guessPegs);
		Pattern guess = generateCode(guessPegs.iterator());


		// when guess for once
		GuessResult guessResult = board.guess(guess);
		LOGGER.info("GuessResult: " + guessResult.toString());

		// then
		assertNotNull(guessResult);
		assertEquals(2, guessResult.getColoredPegs());
		assertEquals(2, guessResult.getWhitePegs());
		assertFalse(guessResult.isExactMatch());
		assertTrue(board.leftGames());
		
		LOGGER.info("Board: " + board.toString());

	}

	@Test
	public void gussMatchingOnlyColors() {
		// given a guess
		Pattern guess = generateCode(Color.GREEN,Color.BLUE, Color.YELLOW,Color.GREEN);

		// when guess for once
		GuessResult guessResult = board.guess(guess);
		LOGGER.info("GuessResult: " + guessResult.toString());

		// then
		assertNotNull(guessResult);
		assertEquals(0, guessResult.getColoredPegs());
		assertEquals(3, guessResult.getWhitePegs());
		assertFalse(guessResult.isExactMatch());
		assertTrue(board.leftGames());
		LOGGER.info("Board: " + board.toString());

	}

	@Test
	public void guessMatchesCode() {
		// given
		Pattern guess = DEFAULT_CODE;
		// when guess for once
		GuessResult guessResult = board.guess(guess);
		LOGGER.info("GuessResult: " + guessResult.toString());

		// then
		assertNotNull(guessResult);
		assertEquals(DEFAULT_CODE.length(), guessResult.getColoredPegs());
		assertEquals(0, guessResult.getWhitePegs());
		assertTrue(guessResult.isExactMatch());
		assertFalse(board.leftGames());
		LOGGER.info("Board: " + board.toString());
	}

}
