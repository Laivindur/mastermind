package com.cyeste.games.mastermind.domain.port;

import java.util.Collection;

import com.cyeste.games.mastermind.domain.DecodingBoard;

/**
 * 
 * @author Christian Yeste Vidal
 *
 */
public interface DecodingBoardsRepository {

	void store(DecodingBoard board);
	
	Collection<DecodingBoard> getAllBoards();
	
	DecodingBoard findById(String id);
	
	
}
