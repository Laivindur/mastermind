package com.cyeste.games.mastermind.domain;

import static com.cyeste.games.mastermind.domain.utils.BoardUtils.generateCode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class DecodingBoardCreationTest {
	
	private static final Pattern DEFAULT_CODE = generateCode(Peg.Color.BLUE, Peg.Color.GREEN, Peg.Color.GREEN,Peg.Color.YELLOW);
	
	
	@Test(expected=IllegalArgumentException.class)
	public void createWithNegativeMaxGAmesSize() {
		DecodingBoard.createBoard("id", -1, DEFAULT_CODE);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createWithNoCode() {
		DecodingBoard.createBoard("id", 1, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createWithNoId() {
		DecodingBoard.createBoard(null, 1, DEFAULT_CODE);
	}
	
	@Test
	public void initialTest() {
		//given
		DecodingBoard board = DecodingBoard.createBoard("id", 1, DEFAULT_CODE);
		
		//then
		assertFalse( board.isSolved());
		assertEquals(DEFAULT_CODE.length(), board.getCode().length());
		assertFalse(board.games().hasNext());
		assertTrue(board.leftGames());
	}
}
