package com.cyeste.games.mastermind.domain.port;

import java.io.Serializable;
import java.util.Collection;

import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.PlayerBoard;

public interface PlayerBoardsRepository {

	void store(PlayerBoard playerBoard);
	
	PlayerBoard findPlayerBoard(Serializable id);
	
	Collection<PlayerBoard> findPlayerBoards(Player  player);
	
	Collection<PlayerBoard> findPlayerBoardsAsCodeMaker(Player player);
	
	Collection<PlayerBoard> findPlayerBoardsAsCodeBreaker(Player player);
	
	
}
