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
public class CreatePlayerBoardAsCodeBreakerTest {

	private final static Logger LOGGER = Logger.getLogger(CreatePlayerBoardAsCodeBreakerTest.class.getName());
	private static final String DEFAULT_ID = "id";
	
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
	private PlayerBoard existingPlayerBoardAsCodeBreaker;
	@Mock
	private PlayerBoard existingPlayerBoardAsCodeMaker;

	private CreatePlayerBoard useCaseInterpreter;
	
	
	
	@Before
	public void setUp() {
		useCaseInterpreter = new CreatePlayerBoard(repository, idGenerator);
		when(codeBreaker.getId()).thenReturn(DEFAULT_ID+"codeBreaker");
		when(board.getId()).thenReturn(DEFAULT_ID);
				
		when(existingPlayerBoardAsCodeBreaker.getPlayer()).thenReturn(codeBreaker);
		when(existingPlayerBoardAsCodeBreaker.getBoard()).thenReturn(board);
		when(existingPlayerBoardAsCodeBreaker.isCoder()).thenReturn(false);
		
		when(existingPlayerBoardAsCodeMaker.isCoder()).thenReturn(true);
	}
			
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToNoCodeBreaker() {
		useCaseInterpreter.joinBoardAsCodeBreaker(null,null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToNoBoardForCodeBreaker() {
		useCaseInterpreter.joinBoardAsCodeBreaker(codeBreaker,null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToPlayerIsAlreadyCodeMaker() {
		when(repository.findPlayerBoard(codeBreaker, board)).thenReturn(existingPlayerBoardAsCodeMaker);
		useCaseInterpreter.joinBoardAsCodeBreaker(codeBreaker, board);
	}
	
	@Test
	public void noSideEffectsIfAlreadyExist() {
		when(repository.findPlayerBoard(codeBreaker, board)).thenReturn(existingPlayerBoardAsCodeBreaker);
		PlayerBoard playerBoardA = useCaseInterpreter.joinBoardAsCodeBreaker(codeBreaker, board);
		assertNotNull(playerBoardA);
		LOGGER.info("PlayerBoard A: " + playerBoardA);
		PlayerBoard playerBoardB = useCaseInterpreter.joinBoardAsCodeBreaker(codeBreaker, board);
		assertNotNull(playerBoardB);
		LOGGER.info("PlayerBoard B: " + playerBoardA);
		assertEquals(playerBoardA.getId(), playerBoardB.getId());	
		assertEquals(playerBoardA.getPlayer().getId(), playerBoardB.getPlayer().getId());
		assertEquals(playerBoardA.getBoard().getId(), playerBoardB.getBoard().getId());
		verify(repository, atLeastOnce()).findPlayerBoard(codeBreaker, board);
	}
	
	@Test
	public void createdSuccessfully() {
		when(idGenerator.generate()).thenReturn(DEFAULT_ID);
		when(repository.findPlayerBoard(codeBreaker, board)).thenReturn(null);
		PlayerBoard playerBoardA = useCaseInterpreter.joinBoardAsCodeBreaker(codeBreaker, board);
		assertNotNull(playerBoardA);
		LOGGER.info("PlayerBoard A: " + playerBoardA);
		verify(idGenerator, atLeastOnce()).generate();
		verify(repository, atLeastOnce()).findPlayerBoard(codeBreaker, board);
	}
	
}
