package com.cyeste.games.mastermind.usescases.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.cyeste.games.mastermind.domain.DecodingBoard;
import com.cyeste.games.mastermind.domain.Pattern;
import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.PlayerBoard;
import com.cyeste.games.mastermind.domain.exception.BoardClosedException;
import com.cyeste.games.mastermind.domain.port.DecodingBoardsRepository;

@RunWith(BlockJUnit4ClassRunner.class)
public class GuessBoardTest {

	private static final String DEFAULT_NAME = "Han Solo";
	private final static Logger LOGGER = Logger.getLogger(GuessBoardTest.class.getName());

	private static final String DEFAULT_ID = "id";
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	public DecodingBoardsRepository repository;	
	
	private DecodingBoard board = DecodingBoard.createBoard(DEFAULT_ID, 1, Pattern.builder().addPeg("GREEN").build());
	private Player player = Player.builder().id(DEFAULT_ID).name(DEFAULT_NAME).build();
	
	private GuessBoard useCaseInterpreter;
	
	@Before
	public void setUp() {
		useCaseInterpreter = new GuessBoard(repository);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToNoArguments() {
		useCaseInterpreter.guess(null,null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToNoPlayerBoard() {
		useCaseInterpreter.guess(null, new String[] {});
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToNoGuess() {
		useCaseInterpreter.guess(PlayerBoard.playerCodeBreaker(DEFAULT_ID, player, board),null);
	}
	
	@Test(expected=BoardClosedException.class)
	public void errorDueToNoLeftMoreGames() {
		useCaseInterpreter.guess(PlayerBoard.playerCodeBreaker(DEFAULT_ID, player, board), new String[] {"BLUE"} );
		useCaseInterpreter.guess(PlayerBoard.playerCodeBreaker(DEFAULT_ID, player, board), new String[] {"BLUE"} );
	}
	
	@Test()
	public void guessOkAndSolved() {		
		DecodingBoard board = DecodingBoard.createBoard(DEFAULT_ID, 1, Pattern.builder().addPeg("GREEN").build());
		useCaseInterpreter.guess(PlayerBoard.playerCodeBreaker(DEFAULT_ID, player, board), new String[] {"GREEN"} );		
		assertNotNull(board.getId());
		assertNotNull(board.getCode());
		assertNotNull(board.games());
		assertTrue(board.isSolved());
		assertFalse(board.leftGames());
		verify(repository, atLeastOnce()).store(board);
		LOGGER.info("Updated board: " + board);

	}
	
	@Test()
	public void guessOkAndNotSolved() {		
		DecodingBoard board = DecodingBoard.createBoard(DEFAULT_ID, 1, Pattern.builder().addPeg("GREEN").build());
		useCaseInterpreter.guess(PlayerBoard.playerCodeBreaker(DEFAULT_ID, player, board), new String[] {"YELLOW"} );		
		assertNotNull(board.getId());
		assertNotNull(board.getCode());
		assertNotNull(board.games());
		assertFalse(board.isSolved());
		assertFalse(board.leftGames());
		verify(repository, atLeastOnce()).store(board);
		LOGGER.info("Updated board: " + board);

	}
}
