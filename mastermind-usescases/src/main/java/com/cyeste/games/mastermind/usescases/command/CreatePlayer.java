package com.cyeste.games.mastermind.usescases.command;

import java.io.Serializable;

import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.port.IdGenerator;
import com.cyeste.games.mastermind.domain.port.PlayersRepository;

public class CreatePlayer {

	private final PlayersRepository store;
	private final IdGenerator<Serializable> idGenerator;
	
	public CreatePlayer(PlayersRepository repository, IdGenerator<Serializable> idGenerator) {
		this.store = repository;
		this.idGenerator = idGenerator;
	}
	
	public Player create(String name) {
		Player player = Player.builder().id(idGenerator.generate()).name(name).build();
		store.store(player);
		return player;
	}
}

