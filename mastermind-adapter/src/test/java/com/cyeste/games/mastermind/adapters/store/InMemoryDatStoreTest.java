package com.cyeste.games.mastermind.adapters.store;

import static org.junit.Assert.assertEquals;

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
public class InMemoryDatStoreTest {

	private static final String DEFAULT_NAME = "Han Solo";
	private static final String DEFAULT_ID = "id";
	private final static Logger LOGGER = Logger.getLogger(InMemoryDatStoreTest.class.getName());
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	private Player newPlayer = Player.builder().id(DEFAULT_ID).name(DEFAULT_NAME).build();
	
	private PlayersRepository repository;
	
	@Before
	public void setUp() {
		repository = new InMemoryPlayersStore();
	}
	
	@Test
	public void storePlayerOnce() {
		repository.store(newPlayer);
		LOGGER.info("Player created: " + newPlayer);
		
		Player playerFound = repository.findPlayer(DEFAULT_ID);
		assertEquals(newPlayer.getId(), playerFound.getId());		
		LOGGER.info("Player found: " + newPlayer);
	}
	
}
