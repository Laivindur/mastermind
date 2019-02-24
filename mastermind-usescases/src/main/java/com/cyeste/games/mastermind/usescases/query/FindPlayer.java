package com.cyeste.games.mastermind.usescases.query;

import java.io.Serializable;

import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.port.PlayersRepository;

public class FindPlayer {

	private final PlayersRepository store;
	
	public FindPlayer(PlayersRepository store) {
		this.store = store;
	}
	
	public Player find(Serializable id) {
		return store.findPlayer(id);
	}                                  	
}
