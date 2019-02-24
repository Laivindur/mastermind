package com.cyeste.games.mastermind.domain;

import com.cyeste.games.mastermind.domain.utils.Validations;

/**
 * Componente de tipo Entity que representa un jugador de Mastermind.
 * @author Christian Yeste Vidal
 *
 */
public class Player {

	private static final String TO_STRING = "{id: \"%s\", name: \"%s\"}";
	private String name;
	private String id;
	
	private Player(String id, String name){
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
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
		private String id;
		
		PlayerBuilder() {
		}
		
		public PlayerBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public PlayerBuilder id(String id) {
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
