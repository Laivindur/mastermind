package com.cyeste.games.mastermind.usescases.query;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
	}

	
	@Test
	public void noBoardsFoundDueToNoPlayer() {
		when(repository.findBoards(any())).thenReturn(Collections.emptyList());
		Iterator<PlayerBoard> playerBoard = useCaseInterpreter.findAllPlayersBoard(null);
		assertNotNull(playerBoard);
		assertFalse(playerBoard.hasNext());
		verify(repository).findBoards(null);
	}
	
	@Test
	public void boardsFound() {
		when(repository.findBoards(eq(player))).thenReturn(Arrays.asList(playerBoard));
		Iterator<PlayerBoard> playerBoard = useCaseInterpreter.findAllPlayersBoard(player);
		assertNotNull(playerBoard);
		assertTrue(playerBoard.hasNext());
		verify(repository).findBoards(player);
	}
	
	/**/
	
	@Test
	public void noPlayersFoundDueToNoBoard() {
		when(repository.findPlayers(any())).thenReturn(Collections.emptyList());
		Iterator<PlayerBoard> playerBoard = useCaseInterpreter.findAllBoardsPlayers(null);
		assertNotNull(playerBoard);
		assertFalse(playerBoard.hasNext());
		verify(repository).findPlayers(null);
	}

	@Test
	public void playersFound() {
		when(repository.findPlayers(eq(board))).thenReturn(Arrays.asList(playerBoard));
		Iterator<PlayerBoard> playerBoard = useCaseInterpreter.findAllBoardsPlayers(board);
		assertNotNull(playerBoard);
		assertTrue(playerBoard.hasNext());
		verify(repository).findPlayers(board);
	}
	/**/
	
	@Test
	public void noPlayerBoardFound() {
		when(repository.findById(any())).thenReturn(null);
		PlayerBoard playerBoard = useCaseInterpreter.findById("unkown");
		assertNull(playerBoard);
		verify(repository).findById("unkown");
	}
	
	@Test
	public void noPayerBoardFoundDueToNoId() {
		when(repository.findById(any())).thenReturn(null);
		PlayerBoard playerBoard = useCaseInterpreter.findById(null);
		assertNull(playerBoard);
		verify(repository).findById(null);
	}

	@Test
	public void playerBoardFoundById() {
		when(repository.findById(eq(DEFAULT_ID))).thenReturn(playerBoard);
		PlayerBoard playerBoard = useCaseInterpreter.findById(DEFAULT_ID);
		assertNotNull(playerBoard);
		verify(repository).findById(DEFAULT_ID);
	}
	
	/**/
	@Test
	public void noPlayerBoardFoundDueToNoPlayer() {
		when(repository.findPlayerBoard(any(),any())).thenReturn(null);	
		PlayerBoard playerBoard = useCaseInterpreter.findPlayerBoard(null, board);
		assertNull(playerBoard);
		verify(repository).findPlayerBoard(null, board);
	}
	
	@Test
	public void noPlayerBoardFoundDueToNoBoard() {
		when(repository.findPlayerBoard(any(),any())).thenReturn(null);	
		PlayerBoard playerBoard = useCaseInterpreter.findPlayerBoard(player, null);
		assertNull(playerBoard);
		verify(repository).findPlayerBoard(player, null);
	}
	
	@Test
	public void noPlayerBoardFoundDueToAlllNulls() {
		when(repository.findPlayerBoard(any(),any())).thenReturn(null);	
		PlayerBoard playerBoard = useCaseInterpreter.findPlayerBoard(null, null);
		assertNull(playerBoard);
		verify(repository).findPlayerBoard(null, null);
	}
	
	@Test
	public void playerBoardFoundByEntities() {
		when(repository.findPlayerBoard(eq(player),eq(board))).thenReturn(playerBoard);
		PlayerBoard playerBoard = useCaseInterpreter.findPlayerBoard(player, board);
		assertNotNull(playerBoard);
		verify(repository).findPlayerBoard(player, board);
	}

}
