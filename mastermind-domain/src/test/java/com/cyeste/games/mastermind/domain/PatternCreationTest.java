package com.cyeste.games.mastermind.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class PatternCreationTest {

	private static final Set<Peg> DEFAULT_PEGS = new LinkedHashSet<Peg>();
	
	@BeforeClass
	public static void setUp() {
		DEFAULT_PEGS.add(new Peg(Peg.Color.BLUE));
		DEFAULT_PEGS.add(new Peg(Peg.Color.GREEN));
		DEFAULT_PEGS.add(new Peg(Peg.Color.PINK));
		DEFAULT_PEGS.add(new Peg(Peg.Color.YELLOW));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createPatternWithoutPegs() {
		new Pattern(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createPatternWithEmptyPegs() {
		new Pattern(Collections.emptySet());
	}
	
	@Test
	public void createdSuccessfully(){
		Pattern pattern = new Pattern(DEFAULT_PEGS);
		assertNotNull(pattern);
		assertEquals(DEFAULT_PEGS.size(), pattern.length());
	}
	
	@Test
	public void clonePattern() {
		Pattern pattern = new Pattern(DEFAULT_PEGS);
		Pattern clone = pattern.clone();
		assertFalse(pattern == clone);	}
}
