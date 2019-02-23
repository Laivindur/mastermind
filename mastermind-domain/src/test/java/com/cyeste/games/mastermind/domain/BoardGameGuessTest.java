package com.cyeste.games.mastermind.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class BoardGameGuessTest {
	
	private static final List<Peg> DEFAULT_PEGS = new LinkedList<Peg>();
	private Pattern code;
	
	@BeforeClass
	public static void populateCodePegs() {
		DEFAULT_PEGS.add(new Peg(Peg.Color.BLUE));
		DEFAULT_PEGS.add(new Peg(Peg.Color.GREEN));
		DEFAULT_PEGS.add(new Peg(Peg.Color.GREEN));
		DEFAULT_PEGS.add(new Peg(Peg.Color.YELLOW));
		assertFalse(DEFAULT_PEGS.isEmpty());
		assertTrue(DEFAULT_PEGS.size() == 4);
		System.out.println(Arrays.toString(DEFAULT_PEGS.toArray()));
	}

	@Before
	public void setUp() {
		code = new Pattern(DEFAULT_PEGS);
	}

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
		DecodingBoard board = new DecodingBoard(1, code );
		
		//then
		assertFalse( board.isSolved());
		assertEquals(code.length(), board.getCode().length());
		assertFalse(board.games().hasNext());
		assertTrue(board.leftGames());
	}
}
