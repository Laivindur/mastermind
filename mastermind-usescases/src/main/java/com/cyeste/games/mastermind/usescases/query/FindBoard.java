package com.cyeste.games.mastermind.usescases.query;

import java.io.Serializable;
import java.util.Iterator;

import com.cyeste.games.mastermind.domain.DecodingBoard;
import com.cyeste.games.mastermind.domain.port.DecodingBoardsRepository;

public class FindBoard {

	private DecodingBoardsRepository store;

	public FindBoard(DecodingBoardsRepository store) {
		this.store = store;
	}
	
	public DecodingBoard find(Serializable id) {
		return store.findBoardById(id);
	}
	
	public Iterator<DecodingBoard> findAll(){
		return store.getAllBoards().iterator();
	}
}
