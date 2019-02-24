package com.cyeste.games.mastermind.usescases.query;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

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
import com.cyeste.games.mastermind.domain.port.PlayerBoardsRepository;

/**
 * 
 * @author Christian Yeste Vidal
 *
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class FindPlayerBoardsUseCasesTest {

	private static final String DEFAULT_NAME = "Han Solo";
	private static final String DEFAULT_ID = "id";
	private Player player = Player.builder().id(DEFAULT_ID).name(DEFAULT_NAME).build();
	private DecodingBoard board = DecodingBoard.createBoard(DEFAULT_ID, 5,
			Pattern.builder().addPeg("GREEN").addPeg("BLUE").addPeg("YELLOW").addPeg("CYAN").build());

	private PlayerBoard playerBoard = PlayerBoard.playerCodeMaker(DEFAULT_ID, player, board);
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	public PlayerBoardsRepository repository;

	private FindPlayerBoards useCaseInterpreter;

	@Before
	public void setUp() {
		useCaseInterpreter = new FindPlayerBoards(repository);
		when(repository.findPlayerBoards(any())).thenReturn(Collections.emptyList());
		when(repository.findPlayerBoardsAsCodeMaker(any())).thenReturn(Collections.emptyList());
		when(repository.findPlayerBoardsAsCodeBreaker(any())).thenReturn(Collections.emptyList());
		when(repository.findPlayerBoard(any())).thenReturn(null);
		when(repository.findPlayerBoard(eq(DEFAULT_ID))).thenReturn(playerBoard);

	}

	@Test
	public void noPlayerBoardsFound() {
		Iterator<PlayerBoard> playerBoard = useCaseInterpreter.findAllBoards(player);
		assertNotNull(playerBoard);
		assertFalse(playerBoard.hasNext());
		verify(repository).findPlayerBoards(player);
	}

	@Test
	public void noPlayerBoardsAsCodeMakerNotFound() {
		Iterator<PlayerBoard> playerBoard = useCaseInterpreter.findPlayerBoardsAsCodeMaker(player);
		assertNotNull(playerBoard);
		assertFalse(playerBoard.hasNext());
		verify(repository).findPlayerBoardsAsCodeMaker(player);
	}
	
	@Test
	public void noPlayerBoardsAsCodeBreakerNotFound() {
		Iterator<PlayerBoard> playerBoard = useCaseInterpreter.findPlayerBoardsAsCodeBreaker(player);
		assertNotNull(playerBoard);
		assertFalse(playerBoard.hasNext());
		verify(repository).findPlayerBoardsAsCodeBreaker(player);
	}
	
	@Test
	public void playerBoardsAsCodeMakerFound() {
		when(repository.findPlayerBoardsAsCodeMaker(any())).thenReturn(Arrays.asList(playerBoard));

		Iterator<PlayerBoard> playerBoard = useCaseInterpreter.findPlayerBoardsAsCodeMaker(player);
		assertNotNull(playerBoard);
		assertTrue(playerBoard.hasNext());
		verify(repository).findPlayerBoardsAsCodeMaker(player);
	}
	

	@Test
	public void playerBoardsAsCodeBreakerFound() {
		when(repository.findPlayerBoardsAsCodeBreaker(any())).thenReturn(Arrays.asList(playerBoard));

		Iterator<PlayerBoard> playerBoard = useCaseInterpreter.findPlayerBoardsAsCodeBreaker(player);
		assertNotNull(playerBoard);
		assertTrue(playerBoard.hasNext());
		verify(repository).findPlayerBoardsAsCodeBreaker(player);
	}




}
