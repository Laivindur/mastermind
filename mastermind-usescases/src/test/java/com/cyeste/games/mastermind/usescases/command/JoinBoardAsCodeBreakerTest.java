package com.cyeste.games.mastermind.usescases.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import java.io.Serializable;
import java.util.Arrays;
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
import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.PlayerBoard;
import com.cyeste.games.mastermind.domain.port.IdGenerator;
import com.cyeste.games.mastermind.domain.port.PlayerBoardsRepository;

@RunWith(BlockJUnit4ClassRunner.class)
public class JoinBoardAsCodeBreakerTest {

	private final static String DEFAULT_ID = "id";
	private final static Logger LOGGER = Logger.getLogger(JoinBoardAsCodeBreakerTest.class.getName());

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	public PlayerBoardsRepository repository;
	
	@Mock
	public IdGenerator<Serializable> idGenerator;
	@Mock
	private Player codeBreaker;
	
	@Mock
	private DecodingBoard board;
	@Mock
	private PlayerBoard existingPlayerBoardAsCodeMaker;
	@Mock
	private PlayerBoard existingPlayerBoardAsCodeBreaker;

	private JoinBoard useCaseInterpreter;
	
	
	
	@Before
	public void setUp() {
		useCaseInterpreter = new JoinBoard(repository, idGenerator);		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToNoCodeBreaker() {
		useCaseInterpreter.joinAsCodeBreaker(null,null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToNoBoardForCodeBreaker() {
		useCaseInterpreter.joinAsCodeBreaker(codeBreaker,null);
	}	
	
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToNoCodeBreakerForTheBoard() {
		useCaseInterpreter.joinAsCodeBreaker(null,board);
	}	
		
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToPlayerIsAlreadyCodeMaker() {
		when(existingPlayerBoardAsCodeMaker.isCoder()).thenReturn(true);
		when(repository.findPlayerBoard(codeBreaker, board)).thenReturn(existingPlayerBoardAsCodeMaker);
		useCaseInterpreter.joinAsCodeBreaker(codeBreaker, board);
	}
	
	@Test
	public void existingPlayerBoardAsCodeBreaker() {
		when(idGenerator.generate()).thenReturn(DEFAULT_ID);
		when(repository.findPlayerBoard(codeBreaker, board)).thenReturn(existingPlayerBoardAsCodeBreaker);
		when(existingPlayerBoardAsCodeBreaker.isBreaker()).thenReturn(true);
		PlayerBoard playerBoard = useCaseInterpreter.joinAsCodeBreaker(codeBreaker, board);
		assertNotNull(playerBoard);
		assertTrue(playerBoard.isBreaker());
		assertFalse(playerBoard.isCoder());
		verify(idGenerator, never()).generate();
		verify(repository, never()).store(existingPlayerBoardAsCodeBreaker);
		LOGGER.info("Existing (Mocked) PlayerBoard: " + playerBoard);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToNotEnoughRoom() {
		when(repository.findPlayers(eq(board))).thenReturn(Arrays.asList(existingPlayerBoardAsCodeMaker,existingPlayerBoardAsCodeBreaker));
		useCaseInterpreter.joinAsCodeBreaker(codeBreaker, board);
	}
	
	@Test
	public void newPlayerBoardAsBreaker() {
		when(idGenerator.generate()).thenReturn("newId");
		when(repository.findPlayerBoard(codeBreaker, board)).thenReturn(existingPlayerBoardAsCodeMaker);
		PlayerBoard playerBoard = useCaseInterpreter.joinAsCodeBreaker(codeBreaker, board);
		assertNotNull(playerBoard);
		assertTrue(playerBoard.isBreaker());
		assertFalse(playerBoard.isCoder());
		assertTrue("newId".equals(playerBoard.getId()));
		verify(idGenerator, only()).generate();
		verify(repository, atLeastOnce()).findPlayers(board);
		verify(repository, atLeastOnce()).store(playerBoard);
		LOGGER.info("New (Created) PlayerBoard: " + playerBoard);
	}
	
}
