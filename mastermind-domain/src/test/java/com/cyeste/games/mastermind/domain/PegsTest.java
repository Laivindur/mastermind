package com.cyeste.games.mastermind.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class PegsTest {

	@Test(expected=IllegalArgumentException.class)
	public void createPegWithoutColor(){
		new Peg(null);
	}
}
