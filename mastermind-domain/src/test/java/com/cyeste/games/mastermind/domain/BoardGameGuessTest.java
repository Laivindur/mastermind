package com.cyeste.games.mastermind.domain;

import static com.cyeste.games.mastermind.domain.utils.BoardUtils.generateCode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class BoardGameGuessTest {
	
	private static final Pattern DEFAULT_CODE = generateCode(Peg.Color.BLUE, Peg.Color.GREEN, Peg.Color.GREEN,Peg.Color.YELLOW);
	

	@Test(expected=IllegalArgumentException.class)
	public void createdWithNoGames() {
		new DecodingBoard(0, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createdWithNullPatern() {
		new DecodingBoard(1, null);
	}
	
	@Test
	public void initialTest() {
		//given
		DecodingBoard board = new DecodingBoard(1, DEFAULT_CODE );
		
		//then
		assertFalse( board.isSolved());
		assertEquals(DEFAULT_CODE.length(), board.getCode().length());
		assertFalse(board.games().hasNext());
		assertTrue(board.leftGames());
	}
}
