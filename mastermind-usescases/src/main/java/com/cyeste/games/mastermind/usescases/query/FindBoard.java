package com.cyeste.games.mastermind.usescases.query;

import java.util.Iterator;

import com.cyeste.games.mastermind.domain.DecodingBoard;
import com.cyeste.games.mastermind.domain.port.DecodingBoardsRepository;

/**
 * Componente capturador de casos de uso relacionados con  la búsqueda de tableros
 * @author Christian Yeste Vidal
 *
 */
public class FindBoard {

	private DecodingBoardsRepository store;

	public FindBoard(DecodingBoardsRepository store) {
		this.store = store;
	}
	
	public DecodingBoard find(String id) {
		return store.findById(id);
	}
	
	public Iterator<DecodingBoard> findAll(){
		return store.getAllBoards().iterator();
	}
}
