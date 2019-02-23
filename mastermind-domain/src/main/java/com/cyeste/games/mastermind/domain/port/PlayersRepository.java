package com.cyeste.games.mastermind.domain.port;

import java.io.Serializable;

import com.cyeste.games.mastermind.domain.Player;

public interface PlayersRepository {

	void store(Player player);
	
	Player findPlayer(Serializable id);
}
