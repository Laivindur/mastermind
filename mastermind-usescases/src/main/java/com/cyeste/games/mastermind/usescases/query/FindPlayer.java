package com.cyeste.games.mastermind.usescases.query;

import java.io.Serializable;

import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.port.PlayersRepository;

public class FindPlayer {

	private final PlayersRepository repository;
	
	public FindPlayer(PlayersRepository repository) {
		this.repository = repository;
	}
	
	public Player find(Serializable id) {
		return repository.findPlayer(id);
	}                                  	
}
