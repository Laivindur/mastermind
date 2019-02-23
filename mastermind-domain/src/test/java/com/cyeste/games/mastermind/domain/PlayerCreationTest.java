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
	private final static Logger LOGGER = Logger.getLogger(DecodingBoardGuessTest.class.getName());
	
	

	@Test(expected = IllegalArgumentException.class)
	public void withNullName() {
		Player.builder().name(null).build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void withEmptyName() {
		Player.builder().name("").build();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void withNoId() {
		Player.builder().name(DEFAULT_NAME).build();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void withNullifiedId() {
		Player.builder().name(DEFAULT_NAME).id(null).build();
	}
	
	@Test
	public void initialState() {
		Player player = Player.builder().name(DEFAULT_NAME).id("id").build();
		assertNotNull(player);
		assertEquals(DEFAULT_NAME, player.getName());
		LOGGER.info("Player: " + player);
	}

}
