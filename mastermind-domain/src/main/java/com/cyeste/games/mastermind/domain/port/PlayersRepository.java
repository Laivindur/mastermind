package com.cyeste.games.mastermind.domain.port;

import java.io.Serializable;
import java.util.Collection;

import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.PlayerBoard;

public interface PlayersRepository {

	Serializable addPlayerBoard(PlayerBoard àyerBoard);
	
	Collection<PlayerBoard> getPlayerBoards(Player  player);
	
	Collection<PlayerBoard> getPlayerBoardsAsCodeMaker(Player player);
	
	Collection<PlayerBoard> getPlayerBoardsAsCodeBreaker(Player player);
	
	
}
