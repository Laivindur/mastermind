package com.cyeste.games.mastermind.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.cyeste.games.mastermind.domain.Peg.Color;

@RunWith(BlockJUnit4ClassRunner.class)
public class PatternCreationTest {

	private static final List<Peg> DEFAULT_PEGS = new LinkedList<Peg>();

	@BeforeClass
	public static void setUp() {
		DEFAULT_PEGS.add(new Peg(Peg.Color.BLUE));
		DEFAULT_PEGS.add(new Peg(Peg.Color.GREEN));
		DEFAULT_PEGS.add(new Peg(Peg.Color.GREEN));
		DEFAULT_PEGS.add(new Peg(Peg.Color.YELLOW));
		assertFalse(DEFAULT_PEGS.isEmpty());
		assertTrue(DEFAULT_PEGS.size() == 4);
		System.out.println(Arrays.toString(DEFAULT_PEGS.toArray()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void createPatternWithoutPegs() {
		new Pattern(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void createPatternWithEmptyPegs() {
		new Pattern(Collections.emptyList());
	}

	@Test
	public void createdSuccessfully() {
		Pattern pattern = new Pattern(DEFAULT_PEGS);
		assertNotNull(pattern);
		assertEquals(DEFAULT_PEGS.size(), pattern.length());
	}

	@Test
	public void clonePattern() {
		Pattern pattern = new Pattern(DEFAULT_PEGS);
		Pattern clone = pattern.clone();
		assertFalse(pattern == clone);
	}

	@Test
	public void hasPeg() {
		Pattern pattern = new Pattern(DEFAULT_PEGS);
		assertTrue(pattern.hasPeg(new Peg(Peg.Color.BLUE)));
		assertTrue(pattern.hasPeg(new Peg(Peg.Color.GREEN)));
	}

	@Test
	public void matchingPostions() {
		Pattern pattern = new Pattern(DEFAULT_PEGS);
		assertEquals(pattern.length(), pattern.matchIngPositoins((pattern)));
	}

	@Test
	public void someMatchingsPosition() {
		Pattern pattern = new Pattern(DEFAULT_PEGS);
		List<Peg> alternativePegList = new LinkedList<>(DEFAULT_PEGS);
		Collections.reverse(alternativePegList);
		Pattern guess = new Pattern(alternativePegList);
		assertEquals(2, pattern.matchIngPositoins((guess)));
	}
	
	@Test
	public void noMatchingPositions() {
		Pattern pattern = new Pattern(DEFAULT_PEGS);
		List<Peg> alternativePegList = new LinkedList<Peg>(Arrays.asList(new Peg(Color.CYAN), new Peg(Color.PINK),
				new Peg(Color.BROWN), new Peg(Peg.Color.BLANK)));
		
		Pattern guess = new Pattern(alternativePegList);
		assertEquals(0, pattern.matchIngPositoins((guess)));

		

	}
}
