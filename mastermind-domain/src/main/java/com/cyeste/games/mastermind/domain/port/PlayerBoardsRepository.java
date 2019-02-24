package com.cyeste.games.mastermind.domain.port;

import java.util.Collection;

import com.cyeste.games.mastermind.domain.DecodingBoard;
import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.PlayerBoard;

public interface PlayerBoardsRepository {

	void store(PlayerBoard playerBoard);
	
	PlayerBoard findById(String id);
	
	PlayerBoard findPlayerBoard(Player player, DecodingBoard board);
	
	Collection<PlayerBoard> findBoards(Player player);
	
	Collection<PlayerBoard> findPlayers(DecodingBoard board);
	
	
	
	
}
