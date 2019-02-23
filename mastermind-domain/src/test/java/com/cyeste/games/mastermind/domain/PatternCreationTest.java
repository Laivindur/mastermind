package com.cyeste.games.mastermind.domain;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class PatternCreationTest {

	@Test(expected=IllegalArgumentException.class)
	public void createPatternWithoutPegs() {
		new Pattern(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createPatternWithEmptyPegs() {
		new Pattern(Collections.emptySet());
	}
}
