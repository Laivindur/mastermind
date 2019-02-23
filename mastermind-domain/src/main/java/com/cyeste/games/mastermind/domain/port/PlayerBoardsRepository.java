package com.cyeste.games.mastermind.domain.port;

import java.io.Serializable;

import com.cyeste.games.mastermind.domain.Player;

public interface PlayerBoardsRepository {

	Serializable addPlayer(Player player);
	
	
}
