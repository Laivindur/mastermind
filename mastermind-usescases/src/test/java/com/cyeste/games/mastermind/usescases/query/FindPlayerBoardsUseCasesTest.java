package com.cyeste.games.mastermind.usescases.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import java.util.Collections;

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

	}

	@Test
	public void noPlayerBoardFound() {
		PlayerBoard playerBoard = repository.findPlayerBoard("unkown");
		assertNull(playerBoard);
		verify(repository).findPlayerBoard("unkown");
	}

	@Test
	public void playerFound() {
		when(repository.findPlayerBoard(eq(DEFAULT_ID))).thenReturn(PlayerBoard.playerCodeMaker(DEFAULT_ID, player, board));
		PlayerBoard playerBoard = repository.findPlayerBoard(DEFAULT_ID);
		assertNotNull(player);
		assertEquals(DEFAULT_ID, playerBoard.getId());
		assertEquals(DEFAULT_NAME, playerBoard.getPlayer().getName());
		assertEquals(DEFAULT_ID, playerBoard.getPlayer().getId());
		assertEquals(DEFAULT_ID, playerBoard.getBoard().getId());
		verify(repository).findPlayerBoard(DEFAULT_ID);

	}
}
