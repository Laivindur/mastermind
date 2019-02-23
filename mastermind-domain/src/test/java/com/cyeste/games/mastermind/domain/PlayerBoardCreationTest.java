package com.cyeste.games.mastermind.domain;

import static com.cyeste.games.mastermind.domain.utils.BoardUtils.generateBoard;

import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;


@RunWith(BlockJUnit4ClassRunner.class)
public class PlayerBoardCreationTest {

	private static final String DEFAULT_NAME = "Han Solo";
	private final static Logger LOGGER = Logger.getLogger(BoardGameCreationTest.class.getName());
	private static final Player DEFAULT_PLAYER = Player.builder().name(DEFAULT_NAME).build();
	
	private static final DecodingBoard DEFAULT_BOARD = generateBoard(Peg.Color.GREEN, Peg.Color.GREEN, Peg.Color.GREEN, Peg.Color.YELLOW);


	@Test(expected=IllegalArgumentException.class)
	public void codeMakerWithNoPlayer() {
		PlayerBoard.playerCodeMaker(null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void codeMakerWithNoBoard() {
		PlayerBoard.playerCodeMaker(DEFAULT_PLAYER, null);
	}
	
	@Test
	public void codeMaker() {
		PlayerBoard playerBoard = PlayerBoard.playerCodeMaker(DEFAULT_PLAYER, DEFAULT_BOARD);
		Assert.assertNotNull(playerBoard);
		Assert.assertTrue(playerBoard.isCoder());
		LOGGER.info("PlayingCodeMaker: " + playerBoard);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void codeBreakerWithNoPlayer() {
		PlayerBoard.playerCodeBreaker(null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void codeBreakerWithNoBoard() {
		PlayerBoard.playerCodeBreaker(DEFAULT_PLAYER, null);
	}
	
	@Test
	public void codeBreaker() {
		PlayerBoard playerBoard = PlayerBoard.playerCodeBreaker(DEFAULT_PLAYER, DEFAULT_BOARD);
		Assert.assertNotNull(playerBoard);
		Assert.assertFalse(playerBoard.isCoder());
		LOGGER.info("PlayerCodeBreaker: " + playerBoard);

	}
}
