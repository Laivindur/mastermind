package com.cyeste.games.mastermind.domain.port;

import com.cyeste.games.mastermind.domain.Player;

public interface PlayersRepository {

	void store(Player player);
	
	Player findById(String id);
}
