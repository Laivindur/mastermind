package com.cyeste.games.mastermind.adapters.store;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import com.cyeste.games.mastermind.domain.port.DecodingBoardsRepository;

@RunWith(BlockJUnit4ClassRunner.class)
public class InMemoryBoardStoreTest {

	private static final String DEFAULT_ID = "id";
	private final static Logger LOGGER = Logger.getLogger(InMemoryBoardStoreTest.class.getName());
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	private DecodingBoard newBoard = DecodingBoard.createBoard(DEFAULT_ID, 1, Pattern.builder().addPeg("RED").build());
	
	private DecodingBoardsRepository repository;
	
	@Before
	public void setUp() {
		repository = new InMemoryBoardsStore();
	}
	
	@Test
	public void notFound() {
		DecodingBoard boardFound = repository.findById(DEFAULT_ID);
		assertNull(boardFound);		
		LOGGER.info("Board found: " + boardFound);
	}
	
	@Test
	public void storeOnce() {
		repository.store(newBoard);
		LOGGER.info("Board created: " + newBoard);
		
		DecodingBoard boardFound = repository.findById(DEFAULT_ID);
		assertEquals(newBoard.getId(), boardFound.getId());		
		LOGGER.info("Player found: " + newBoard);
	}
	
		
	@Test
	public void emptyBoardsList() {
		assertTrue(repository.getAllBoards().isEmpty());
	}
	
	@Test
	public void oneBoardList() {
		repository.store(newBoard);
		assertFalse(repository.getAllBoards().isEmpty());
	}
	
	@Test
	public void oneBoardListWithNoRepeats() {
		repository.store(newBoard);
		repository.store(newBoard);
		assertFalse(repository.getAllBoards().isEmpty());
	}
	
	@Test
	public void nBoardListWithNoRepeats() {
		repository.store(newBoard);
		repository.store(DecodingBoard.createBoard("id1", 1, Pattern.builder().addPeg("RED").build()));
		repository.store(DecodingBoard.createBoard("id2", 1, Pattern.builder().addPeg("YELLOW").build()));
		Collection<DecodingBoard> boards = repository.getAllBoards();
		assertThat(boards.isEmpty(), is(false));
		assertThat(boards.size(), is(3));
		boards.parallelStream().forEach(board -> LOGGER.info("Board: " + board));
	}
}
