package com.cyeste.games.mastermind.usescases.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
import com.cyeste.games.mastermind.domain.port.DecodingBoardsRepository;
import com.cyeste.games.mastermind.domain.port.IdGenerator;

@RunWith(BlockJUnit4ClassRunner.class)
public class CreateBoardTest {

	private final static Logger LOGGER = Logger.getLogger(CreateBoardTest.class.getName());

	private static final String DEFAULT_ID = "id";
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	public DecodingBoardsRepository repository;
	
	@Mock
	public IdGenerator<Serializable> idGenerator;

	private CreateBoard useCaseInterpreter;
	
	@Before
	public void setUp() {
		useCaseInterpreter = new CreateBoard(repository, idGenerator);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToNoColorsProvided() {
		useCaseInterpreter.create(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToNoEmptyColorsProvided() {
		useCaseInterpreter.create(new String[] {});
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToUnkownColor() {
		useCaseInterpreter.create(new String[] {"BLACK","CYAN","GREEN","RED"});
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToNotEnoughColors() {
		useCaseInterpreter.create(new String[] {"CYAN","CYAN","GREEN","RED"});
	}
	
	@Test()
	public void createdSuccessfully() {
		when(idGenerator.generate()).thenReturn(DEFAULT_ID);

		DecodingBoard board = useCaseInterpreter.create(new String[] {"BLUE","GREEN","PINK","GREEN","YELLOW"});
		assertNotNull(board);
		assertNotNull(board.getId());
		assertNotNull(board.getCode());
		assertNotNull(board.games());
		assertFalse(board.isSolved());
		assertTrue(board.leftGames());
		verify(idGenerator, atLeastOnce()).generate();
		verify(repository, atLeastOnce()).store(board);
		LOGGER.info("Created board: " + board);

	}
}
