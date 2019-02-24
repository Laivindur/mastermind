package com.cyeste.games.mastermind.adapters.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.port.PlayersRepository;

@RunWith(BlockJUnit4ClassRunner.class)
public class InMemoryPlayersStoreTest {

	private static final String DEFAULT_NAME = "Han Solo";
	private static final String DEFAULT_ID = "id";
	private final static Logger LOGGER = Logger.getLogger(InMemoryPlayersStoreTest.class.getName());
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	private Player newPlayer = Player.builder().id(DEFAULT_ID).name(DEFAULT_NAME).build();
	
	private PlayersRepository repository;
	
	@Before
	public void setUp() {
		repository = new InMemoryPlayersStore();
	}
	
	@Test
	public void notFound() {
		Player playerFound = repository.findById(DEFAULT_ID);
		assertNull(playerFound);		
		LOGGER.info("Player found: " + newPlayer);
	}
	
	@Test
	public void storeOnce() {
		repository.store(newPlayer);
		LOGGER.info("Player created: " + newPlayer);
		
		Player playerFound = repository.findById(DEFAULT_ID);
		assertEquals(newPlayer.getId(), playerFound.getId());		
		LOGGER.info("Player found: " + newPlayer);
	}
	

}
