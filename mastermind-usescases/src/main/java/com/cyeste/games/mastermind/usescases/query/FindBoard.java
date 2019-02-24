package com.cyeste.games.mastermind.usescases.query;

import java.io.Serializable;
import java.util.Iterator;

import com.cyeste.games.mastermind.domain.DecodingBoard;
import com.cyeste.games.mastermind.domain.port.DecodingBoardsRepository;

public class FindBoard {

	private DecodingBoardsRepository repository;

	public FindBoard(DecodingBoardsRepository repository) {
		this.repository = repository;
	}
	
	public DecodingBoard find(Serializable id) {
		return repository.findBoardById(id);
	}
	
	public Iterator<DecodingBoard> findAll(){
		return repository.getAllBoards().iterator();
	}
}
