package com.cyeste.games.mastermind.usescases.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.atLeastOnce;
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
	private static final String DEFAULT_ID = "id";
	
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

	private CreatePlayerBoard useCaseInterpreter;
	
	
	
	@Before
	public void setUp() {
		useCaseInterpreter = new CreatePlayerBoard(repository, idGenerator);
		when(codeMaker.getId()).thenReturn(DEFAULT_ID+"codeMaker");
		when(board.getId()).thenReturn(DEFAULT_ID);
				
		when(existingPlayerBoardAsCodeMaker.getPlayer()).thenReturn(codeMaker);
		when(existingPlayerBoardAsCodeMaker.getBoard()).thenReturn(board);
		when(existingPlayerBoardAsCodeMaker.isCoder()).thenReturn(true);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToNoCodeMaker() {
		useCaseInterpreter.joinBoardAsCodeMaker(null,null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToNoBoardForCodeMaker() {
		useCaseInterpreter.joinBoardAsCodeMaker(codeMaker,null);
	}	
		
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToPlayerIsAlreadyCodeBreaker() {
		when(repository.findPlayerBoard(codeMaker, board)).thenReturn(existingPlayerBoardAsCodeBreaker);
		useCaseInterpreter.joinBoardAsCodeMaker(codeMaker, board);
	}
	
	@Test
	public void noSideEffectsIfAlreadyExist() {
		when(repository.findPlayerBoard(codeMaker, board)).thenReturn(existingPlayerBoardAsCodeMaker);
		PlayerBoard playerBoardA = useCaseInterpreter.joinBoardAsCodeMaker(codeMaker, board);
		assertNotNull(playerBoardA);
		LOGGER.info("PlayerBoard A: " + playerBoardA);
		PlayerBoard playerBoardB = useCaseInterpreter.joinBoardAsCodeMaker(codeMaker, board);
		assertNotNull(playerBoardB);
		LOGGER.info("PlayerBoard B: " + playerBoardA);
		assertEquals(playerBoardA.getId(), playerBoardB.getId());	
		assertEquals(playerBoardA.getPlayer().getId(), playerBoardB.getPlayer().getId());
		assertEquals(playerBoardA.getBoard().getId(), playerBoardB.getBoard().getId());
		verify(repository, atLeastOnce()).findPlayerBoard(codeMaker, board);
	}
	

	@Test
	public void createdSuccessfully() {
		when(idGenerator.generate()).thenReturn(DEFAULT_ID);
		when(repository.findPlayerBoard(codeMaker, board)).thenReturn(null);
		PlayerBoard playerBoardA = useCaseInterpreter.joinBoardAsCodeMaker(codeMaker, board);
		assertNotNull(playerBoardA);
		verify(idGenerator, atLeastOnce()).generate();
		verify(repository, atLeastOnce()).findPlayerBoard(codeMaker, board);
		LOGGER.info("PlayerBoard A: " + playerBoardA);
	}
	
}
