package com.cyeste.games.mastermind.domain;

import java.io.Serializable;

import com.cyeste.games.mastermind.domain.utils.Validations;

public class Player {

	private static final String TO_STRING = "{id: \"%s\", name: \"%s\"}";
	private String name;
	private Serializable id;
	
	private Player(Serializable id, String name){
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public Serializable getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return String.format(TO_STRING, id.toString(), name);
	}
	
	public static PlayerBuilder builder() {
		return new PlayerBuilder();
	}
	
	public final static class PlayerBuilder {
		private String name;
		private Serializable id;
		
		PlayerBuilder() {
		}
		
		public PlayerBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public PlayerBuilder id(Serializable id) {
			this.id = id;
			return this;
		}
				
		public Player build() {
			Validations.whenEmpty(name).throwIllegalArgumentException("Player's name is required. It cant not be null or empty");
			Validations.whenNull(id).throwIllegalArgumentException("Player's id is required. It cant not be null");
			return new Player(id,name);
		}
	}
}
