package com.cyeste.games.mastermind.usescases.command;

import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.port.IdGenerator;
import com.cyeste.games.mastermind.domain.port.PlayersRepository;

/**
 * Componente capturador de casos de uso relacionados con  la creación de jugadores
 * @author Christian Yeste Vidal
 *
 */
public class CreatePlayer {

	private final PlayersRepository store;
	private final IdGenerator idGenerator;
	
	public CreatePlayer(PlayersRepository repository, IdGenerator idGenerator) {
		this.store = repository;
		this.idGenerator = idGenerator;
	}
	
	public Player create(String name) {
		Player player = Player.builder().id(idGenerator.generate()).name(name).build();
		store.store(player);
		return player;
	}
}

