package com.cyeste.games.mastermind.usescases.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.Serializable;
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
public class CreatePlayerBoardAsCodeMakerTest {

	private final static Logger LOGGER = Logger.getLogger(CreatePlayerBoardAsCodeMakerTest.class.getName());

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	public PlayerBoardsRepository repository;
	
	@Mock
	public IdGenerator<Serializable> idGenerator;
	@Mock
	private Player codeMaker;
	
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
	public void errorDueToNoCodeMaker() {
		useCaseInterpreter.joinAsCodeMaker(null,null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToNoBoardForCodeMaker() {
		useCaseInterpreter.joinAsCodeMaker(codeMaker,null);
	}	
	
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToNoCodeMakerForTheBoard() {
		useCaseInterpreter.joinAsCodeMaker(null,board);
	}	
		
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToPlayerIsAlreadyCodeBreaker() {
		when(repository.findPlayerBoard(codeMaker, board)).thenReturn(existingPlayerBoardAsCodeBreaker);
		useCaseInterpreter.joinAsCodeMaker(codeMaker, board);
	}
	
	@Test
	public void existingPlayerBoardAsCodeMaker() {
		when(existingPlayerBoardAsCodeMaker.isCoder()).thenReturn(true);
		when(repository.findPlayerBoard(codeMaker, board)).thenReturn(existingPlayerBoardAsCodeMaker);
		PlayerBoard playerBoard = useCaseInterpreter.joinAsCodeMaker(codeMaker, board);
		assertNotNull(playerBoard);
		assertTrue(playerBoard.isCoder());
		assertFalse(playerBoard.isBreaker());
		verify(idGenerator, never()).generate();
		verify(repository, never()).store(existingPlayerBoardAsCodeMaker);
		LOGGER.info("Existing (Mocked) PlayerBoard: " + playerBoard);
	}
	
	@Test
	public void newPlayerBoardAsMaker() {
		when(idGenerator.generate()).thenReturn("newId");
		when(repository.findPlayerBoard(codeMaker, board)).thenReturn(null);
		PlayerBoard playerBoard = useCaseInterpreter.joinAsCodeMaker(codeMaker, board);
		assertNotNull(playerBoard);
		assertTrue(playerBoard.isCoder());
		assertFalse(playerBoard.isBreaker());
		assertTrue("newId".equals(playerBoard.getId()));
		verify(idGenerator, only()).generate();
		verify(repository, atLeastOnce()).store(playerBoard);
		LOGGER.info("New (Created) PlayerBoard: " + playerBoard);
	}
	
}
