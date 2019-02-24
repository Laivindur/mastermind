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

import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.port.PlayersRepository;

/**
 * 
 * @author Christian Yeste Vidal
 *
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class FindPlayerUseCaseTest {

	private static final String DEFAULT_NAME = "Han Solo";

	private static final String DEFAULT_ID = "id";

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock
	public PlayersRepository repository;
	
	private FindPlayer useCaseInterpreter;
	
	@Before
	public void setUp() {
		useCaseInterpreter = new FindPlayer(repository);
		when(repository.findPlayer(eq(DEFAULT_ID))).thenReturn(Player.builder().id(DEFAULT_ID).name(DEFAULT_NAME).build());
	}
	
	
	@Test
	public void playerNotFound() {
		Player player = useCaseInterpreter.find("unkown");
		assertNull(player);
		verify(repository).findPlayer("unkown");
	}
	
	@Test
	public void playerFound() {
		Player player = useCaseInterpreter.find(DEFAULT_ID);
		assertNotNull(player);
		assertEquals(DEFAULT_ID, player.getId());
		assertEquals(DEFAULT_NAME, player.getName());
		verify(repository).findPlayer(DEFAULT_ID);
		
	}
}
