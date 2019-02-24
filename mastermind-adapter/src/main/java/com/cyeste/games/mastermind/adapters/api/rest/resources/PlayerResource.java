package com.cyeste.games.mastermind.adapters.api.rest.resources;

import java.io.Serializable;

import com.cyeste.games.mastermind.domain.Player;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("player")
public class PlayerResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("id")
	private String id;

	@JsonCreator
	public PlayerResource(@JsonProperty("id") final String id, @JsonProperty("name") final String name) {
		this.name = name;
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public static PlayerResource toResource(Player player) {
		return new PlayerResource(player.getId().toString(),player.getName());
	}
}
