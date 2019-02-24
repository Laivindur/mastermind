package com.cyeste.games.mastermind.adapters.store;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.cyeste.games.mastermind.domain.DecodingBoard;
import com.cyeste.games.mastermind.domain.Pattern;
import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.PlayerBoard;
import com.cyeste.games.mastermind.domain.port.PlayerBoardsRepository;

@RunWith(BlockJUnit4ClassRunner.class)
public class InMemoryPlayerBoardStoreTest {

	private static final String DEFAULT_NAME = "Han Solo";
	private static final String DEFAULT_ID = "id";
	private final static Logger LOGGER = Logger.getLogger(InMemoryPlayerBoardStoreTest.class.getName());
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	private DecodingBoard board = DecodingBoard.createBoard(DEFAULT_ID, 1, Pattern.builder().addPeg("RED").build());
	private Player player = Player.builder().id(DEFAULT_ID).name(DEFAULT_NAME).build();
	private PlayerBoard newPlayerBoard = PlayerBoard.playerCodeMaker(DEFAULT_ID, player, board);
	private PlayerBoardsRepository repository;
	
	@Before
	public void setUp() {
		repository = new InMemoryPalyerAndBoardStore();
	}
	
	@Test
	public void notFound() {
		PlayerBoard playerBoardFound = repository.findById(DEFAULT_ID);
		assertNull(playerBoardFound);		
		LOGGER.info("PlayerBoard found: " + playerBoardFound);
	}
	
	@Test
	public void storeOnce() {
		repository.store(newPlayerBoard);
		LOGGER.info("PlayerBoard created: " + newPlayerBoard);
		
		PlayerBoard playerBoardFound = repository.findById(DEFAULT_ID);
		assertEquals(newPlayerBoard.getId(), playerBoardFound.getId());		
		LOGGER.info("PlayerBoard found: " + playerBoardFound);
	}
	
	@Test
	public void playersBoardsFound() {
		storeOnce();
		Collection<PlayerBoard> boards = repository.findBoards(player);
		assertFalse(boards.isEmpty());
		assertThat(boards.size(), is(1));
	}
	
	@Test
	public void playersBoardsNotFound() {
		Collection<PlayerBoard> boards = repository.findBoards(player);
		assertTrue(boards.isEmpty());
		assertThat(boards.size(), is(0));
	}
		
	@Test
	public void boardsPlayersFound() {
		storeOnce();
		Collection<PlayerBoard> players = repository.findPlayers(board);
		assertFalse(players.isEmpty());
		assertThat(players.size(), is(1));
	}
	
	@Test
	public void boardsPlayersNotFound() {
		Collection<PlayerBoard> players = repository.findPlayers(board);
		assertTrue(players.isEmpty());
		assertThat(players.size(), is(0));
	}
	
	@Test
	public void playerAndBoardFound() {
		storeOnce();
		PlayerBoard playerBoard = repository.findPlayerBoard(player,board);
		assertNotNull(playerBoard);
		assertThat(playerBoard.getPlayer(), is(player));
		assertThat(playerBoard.getBoard(), is(board));
	}
	
	@Test
	public void playerAndBoardNotFound() {
		PlayerBoard playerBoard = repository.findPlayerBoard(player,board);
		assertNull(playerBoard);
	}
}