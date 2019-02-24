package com.cyeste.games.mastermind.usescases.query;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.cyeste.games.mastermind.domain.port.DecodingBoardsRepository;

/**
 * 
 * @author Christian Yeste Vidal
 *
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class FindBoardUseCaseTest {

	private static final String DEFAULT_ID = "id";

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock
	public DecodingBoardsRepository repository;
	
	private DecodingBoard board = DecodingBoard.createBoard(DEFAULT_ID, 1, Pattern.builder().addPeg("GREEN").build());
	
	private FindBoard useCaseInterpreter;
	
	@Before
	public void setUp() {
		useCaseInterpreter = new FindBoard(repository);
		when(repository.findById(eq(DEFAULT_ID))).thenReturn(board);
	}
	
	
	@Test
	public void boardNotFound() {
		DecodingBoard board = useCaseInterpreter.find("unkown");
		assertNull(board);
		verify(repository).findById("unkown");
	}
	
	@Test
	public void playerFound() {
		DecodingBoard board = useCaseInterpreter.find(DEFAULT_ID);
		assertNotNull(board);
		assertEquals(DEFAULT_ID, board.getId());
		verify(repository).findById(DEFAULT_ID);
		
	}
}
