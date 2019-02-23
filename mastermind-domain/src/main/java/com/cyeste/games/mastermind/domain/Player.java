package com.cyeste.games.mastermind.domain;

import com.cyeste.games.mastermind.domain.utils.Validations;

public class Player {

	private static final String TO_STRING = "{name: \"%s\"}";
	private String name;
	
	public Player(String name){
		Validations.whenEmpty(name).throwIllegalArgumentException("Player's name is required. It cant not be null or empty");
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return String.format(TO_STRING, name);
	}
}
