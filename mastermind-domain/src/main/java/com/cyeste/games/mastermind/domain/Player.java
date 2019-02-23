package com.cyeste.games.mastermind.domain;

import com.cyeste.games.mastermind.domain.utils.Validations;

public class Player {

	private static final String TO_STRING = "{name: \"%s\"}";
	private String name;
	
	private Player(String name){
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
	
	public static PlayerBuilder builder() {
		return new PlayerBuilder();
	}
	
	public final static class PlayerBuilder {
		private String name;
		
		PlayerBuilder() {
		}
		
		public PlayerBuilder name(String name) {
			Validations.whenEmpty(name).throwIllegalArgumentException("Player's name is required. It cant not be null or empty");
			this.name = name;
			return this;
		}
		
		public Player build() {
			return new Player(name);
		}
	}
}
