package com.cyeste.games.mastermind.usescases.query;

import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.port.PlayersRepository;

/**
 * Componente capturador de casos de uso relacionados con  la búsqueda de jugadores
 * @author Christian Yeste Vidal
 *
 */
public class FindPlayer {

	private final PlayersRepository store;
	
	public FindPlayer(PlayersRepository store) {
		this.store = store;
	}
	
	public Player find(String id) {
		return store.findById(id);
	}                                  	
}
