package com.cyeste.games.mastermind.domain.port;

import java.io.Serializable;
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
	
	DecodingBoard findBoardById(Serializable id);
	
	
}
