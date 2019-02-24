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

import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.port.IdGenerator;
import com.cyeste.games.mastermind.domain.port.PlayersRepository;

@RunWith(BlockJUnit4ClassRunner.class)
public class CreateUserTest {

	private final static Logger LOGGER = Logger.getLogger(CreateUserTest.class.getName());

	private static final String DEFAULT_NAME = "Han Solo";
	private static final String DEFAULT_ID = "id";
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Mock
	public PlayersRepository repository;
	
	@Mock
	public IdGenerator<Serializable> idGenerator;

	private CreatePlayer useCaseInterpreter;
	
	@Before
	public void setUp() {
		useCaseInterpreter = new CreatePlayer(repository, idGenerator);
		when(idGenerator.generate()).thenReturn(DEFAULT_ID);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToNoNameProvided() {
		useCaseInterpreter.create(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void errorDueToEmptyNameProvided() {
		useCaseInterpreter.create("");
	}
	
	@Test()
	public void createdSuccessfully() {
		Player player = useCaseInterpreter.create(DEFAULT_NAME);
		assertNotNull(player);
		assertEquals(DEFAULT_NAME, player.getName());
		verify(idGenerator,atLeastOnce()).generate();
		verify(repository, atLeastOnce()).store(player);
		LOGGER.info("Created player: " + player);
	}
}
