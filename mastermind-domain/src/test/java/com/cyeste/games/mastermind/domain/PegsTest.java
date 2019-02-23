package com.cyeste.games.mastermind.domain;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.cyeste.games.mastermind.domain.Peg.Color;

@RunWith(BlockJUnit4ClassRunner.class)
public class PegsTest {

	@Test(expected = IllegalArgumentException.class)
	public void createPegWithoutColor() {
		new Peg(null);
	}

	@Test
	public void createPeg() {
		// Given
		Color color = Peg.Color.BLUE;
		// when
		Peg peg = new Peg(color);
		// Then
		assertNotNull(peg);
		assertEquals(peg.getColor(), color);
	}

	@Test
	public void differentPegs() {
		// Given 2 pegs of different colors
		Peg pegBlue = new Peg(Peg.Color.BLUE);
		Peg pegRed = new Peg(Peg.Color.RED);
		// then
		assertFalse(pegBlue.equals(pegRed));
		assertFalse(pegRed.equals(pegBlue));
		assertNotEquals(pegBlue.hashCode(), pegRed.hashCode());
	}

	@Test
	public void samePegs() {
		// Given 2 pegs of different colors
		Peg pegBlue = new Peg(Peg.Color.BLUE);
		// then
		assertTrue(pegBlue.equals(pegBlue));
		assertEquals(pegBlue.hashCode(), pegBlue.hashCode());
	}

	@Test
	public void equalPegs() {
		// Given 2 pegs of different colors
		Peg pegBlue = new Peg(Peg.Color.BLUE);
		Peg pegBlueish = new Peg(Peg.Color.BLUE);
		// then
		assertTrue(pegBlue.equals(pegBlueish));
		assertTrue(pegBlueish.equals(pegBlue));
		assertEquals(pegBlue.hashCode(), pegBlueish.hashCode());
	}
}
