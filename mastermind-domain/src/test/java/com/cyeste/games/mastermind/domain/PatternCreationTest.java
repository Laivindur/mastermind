package com.cyeste.games.mastermind.domain;

import static com.cyeste.games.mastermind.domain.utils.BoardUtils.generateCode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.cyeste.games.mastermind.domain.Peg.Color;

@RunWith(BlockJUnit4ClassRunner.class)
public class PatternCreationTest {


	@Test(expected = IllegalArgumentException.class)
	public void createPatternWithoutPegs() {
		Pattern.builder().build();
	}

	@Test(expected = IllegalArgumentException.class)
	public void createPatternWithInvalidColor() {
		Pattern.builder().addPeg("BLACK").build();
	}

	@Test
	public void createdSuccessfully() {
		Pattern pattern = Pattern.builder().addPeg(Color.BLUE).addPeg("GREEN").addPeg(Color.GREEN).addPeg("YELLOW").build();
		assertNotNull(pattern);
		assertEquals(4, pattern.length());
	}

	@Test
	public void clonePattern() {
		Pattern pattern = Pattern.builder().addPeg(Color.BLUE).addPeg("GREEN").addPeg(Color.GREEN).addPeg("YELLOW").build();
		Pattern clone = pattern.clone();
		assertFalse(pattern == clone);
	}

	@Test
	public void hasPeg() {
		Pattern pattern = Pattern.builder().addPeg(Color.BLUE).addPeg("GREEN").addPeg(Color.GREEN).addPeg("YELLOW").build();
		assertTrue(pattern.hasPeg(Peg.createPeg(Peg.Color.BLUE)));
		assertTrue(pattern.hasPeg(Peg.createPeg(Peg.Color.GREEN)));
	}

	@Test
	public void matchingPostions() {
		Pattern pattern = Pattern.builder().addPeg(Color.BLUE).addPeg("GREEN").addPeg(Color.GREEN).addPeg("YELLOW").build();
		assertEquals(pattern.length(), pattern.matchingPegs((pattern)));
	}

	@Test
	public void someMatchingsPosition() {
		Pattern pattern = Pattern.builder().addPeg(Color.BLUE).addPeg("GREEN").addPeg(Color.GREEN).addPeg("YELLOW").build();
		List<Peg> guessPegs = Arrays.asList(pattern.toArray());
		Collections.reverse(guessPegs);
		Pattern guess = generateCode(guessPegs.iterator());
		assertEquals(2, pattern.matchingPegs((guess)));
	}
	
	@Test
	public void noMatchingPositions() {
		Pattern pattern = Pattern.builder().addPeg(Color.BLUE).addPeg("GREEN").addPeg(Color.GREEN).addPeg("YELLOW").build();		
		Pattern guess =  generateCode(Color.CYAN, Color.PINK,Color.BROWN,Color.BLANK);
		assertEquals(0, pattern.matchingPegs((guess)));
	}
	
	@Test
	public void noMatchingColorsDueToDifferentSet(){
		Pattern pattern = Pattern.builder().addPeg(Color.BLUE).addPeg("GREEN").addPeg(Color.GREEN).addPeg("YELLOW").build();
		Pattern guess =  generateCode(Color.CYAN, Color.PINK,Color.BROWN,Color.BLANK);
		assertEquals(0, pattern.matchingPegColors(guess));

	}
	
	@Test
	public void noMatchingColorsOutOfThePatternCodeRemainingPegs(){
		//given : blue, green, green, yellow
		Pattern pattern = Pattern.builder().addPeg(Color.BLUE).addPeg("GREEN").addPeg(Color.GREEN).addPeg("YELLOW").build();
		// and ... blue, blue, brown, blank
		Pattern guess =  generateCode(Color.BLUE, Color.BLUE,Color.BROWN,Color.BLANK);

		//then
		//2nd Blue is not counted because after matching position[0]=blue, there's no other position[n] = blue
		assertEquals(0, pattern.matchingPegColors(guess));
	}
	
	@Test
	public void matchingColorsDueToPatternCodeRemainingPegs(){
		//given : blue, green, green, yellow
		Pattern pattern = Pattern.builder().addPeg(Color.BLUE).addPeg("GREEN").addPeg(Color.GREEN).addPeg("YELLOW").build();
		// and ... blue, green, brown, green
		Pattern guess =  generateCode(Color.BLUE, Color.GREEN,Color.BROWN,Color.GREEN);
			
		//then
		//2nd Green is counted because after matching position[1]=green, there's yet position[2] = green
		assertEquals(1, pattern.matchingPegColors(guess));
	}
	
	@Test
	public void noMatchingColorsDueToExactMachings(){
		//given : blue, green, green, yellow
		Pattern pattern = Pattern.builder().addPeg(Color.BLUE).addPeg("GREEN").addPeg(Color.GREEN).addPeg("YELLOW").build();
		//then
		//No matching colors are counted if pegs matched position
		assertEquals(0, pattern.matchingPegColors(pattern));
		assertEquals(pattern.length(), pattern.matchingPegs(pattern));

	}
	
	@Test
	public void countingColorsWithNoRepeats(){
		//given : blue, green, green, yellow
		Pattern pattern = Pattern.builder().addPeg(Color.BLUE).addPeg("GREEN").addPeg(Color.GREEN).addPeg("YELLOW").build();
		Pattern guess =  generateCode(Color.GREEN, Color.BLUE,Color.YELLOW,Color.GREEN);

		assertEquals(0, pattern.matchingPegs((guess)));
		assertEquals(3, pattern.matchingPegColors((guess)));


	}



	
}
