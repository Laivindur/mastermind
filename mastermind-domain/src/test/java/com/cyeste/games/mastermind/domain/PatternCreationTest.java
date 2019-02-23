package com.cyeste.games.mastermind.domain;

import static com.cyeste.games.mastermind.domain.utils.BoardUtils.generateCode;
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
		assertEquals(pattern.length(), pattern.matchingPegs((pattern)));
	}

	@Test
	public void someMatchingsPosition() {
		Pattern pattern = new Pattern(DEFAULT_PEGS);
		List<Peg> guessPegs = Arrays.asList(pattern.toArray());
		Collections.reverse(guessPegs);
		Pattern guess = generateCode(guessPegs.iterator());
		assertEquals(2, pattern.matchingPegs((guess)));
	}
	
	@Test
	public void noMatchingPositions() {
		Pattern pattern = new Pattern(DEFAULT_PEGS);		
		Pattern guess =  generateCode(Color.CYAN, Color.PINK,Color.BROWN,Color.BLANK);
		assertEquals(0, pattern.matchingPegs((guess)));
	}
	
	@Test
	public void noMatchingColorsDueToDifferentSet(){
		Pattern pattern = new Pattern(DEFAULT_PEGS);
		Pattern guess =  generateCode(Color.CYAN, Color.PINK,Color.BROWN,Color.BLANK);
		assertEquals(0, pattern.matchingPegColors(guess));

	}
	
	@Test
	public void noMatchingColorsOutOfThePatternCodeRemainingPegs(){
		//given : blue, green, green, yellow
		Pattern pattern = new Pattern(DEFAULT_PEGS);
		// and ... blue, blue, brown, blank
		Pattern guess =  generateCode(Color.BLUE, Color.BLUE,Color.BROWN,Color.BLANK);

		//then
		//2nd Blue is not counted because after matching position[0]=blue, there's no other position[n] = blue
		assertEquals(0, pattern.matchingPegColors(guess));
	}
	
	@Test
	public void matchingColorsDueToPatternCodeRemainingPegs(){
		//given : blue, green, green, yellow
		Pattern pattern = new Pattern(DEFAULT_PEGS);
		// and ... blue, green, brown, green
		Pattern guess =  generateCode(Color.BLUE, Color.GREEN,Color.BROWN,Color.GREEN);
			
		//then
		//2nd Green is counted because after matching position[1]=green, there's yet position[2] = green
		assertEquals(1, pattern.matchingPegColors(guess));
	}
	
	@Test
	public void noMatchingColorsDueToExactMachings(){
		//given : blue, green, green, yellow
		Pattern pattern = new Pattern(DEFAULT_PEGS);
		//then
		//No matching colors are counted if pegs matched position
		assertEquals(0, pattern.matchingPegColors(pattern));
		assertEquals(pattern.length(), pattern.matchingPegs(pattern));

	}
	
	@Test
	public void countingColorsWithNoRepeats(){
		//given : blue, green, green, yellow
		Pattern pattern = new Pattern(DEFAULT_PEGS);
		Pattern guess =  generateCode(Color.GREEN, Color.BLUE,Color.YELLOW,Color.GREEN);

		assertEquals(0, pattern.matchingPegs((guess)));
		assertEquals(3, pattern.matchingPegColors((guess)));


	}



	
}
