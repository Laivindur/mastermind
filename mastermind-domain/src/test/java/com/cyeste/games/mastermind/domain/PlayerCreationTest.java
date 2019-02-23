package com.cyeste.games.mastermind.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class PlayerCreationTest {

	private static final String DEFAULT_NAME = "Han Solo";
	private final static Logger LOGGER = Logger.getLogger(BoardGameCreationTest.class.getName());
	
	

	@Test(expected = IllegalArgumentException.class)
	public void withNullName() {
		new Player(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void withEmptyName() {
		new Player("");
	}
	
	@Test
	public void initialState() {
		Player player = new Player(DEFAULT_NAME);
		assertNotNull(player);
		assertEquals(DEFAULT_NAME, player.getName());
		LOGGER.info("Player: " + player);
	}

}
