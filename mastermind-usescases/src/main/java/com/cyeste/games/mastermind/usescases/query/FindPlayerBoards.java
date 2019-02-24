package com.cyeste.games.mastermind.usescases.query;

import java.io.Serializable;
import java.util.Iterator;

import com.cyeste.games.mastermind.domain.DecodingBoard;
import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.PlayerBoard;
import com.cyeste.games.mastermind.domain.port.PlayerBoardsRepository;

public class FindPlayerBoards {

	private final PlayerBoardsRepository store;

	public FindPlayerBoards(PlayerBoardsRepository store) {
		super();
		this.store = store;
	}
	
	public Iterator<PlayerBoard> findAllPlayersBoard(Player player) {
		return store.findBoards(player).iterator();
	}
	
	public Iterator<PlayerBoard> findAllBoardsPlayers(DecodingBoard board) {
		return store.findPlayers(board).iterator();
	}
	
	public PlayerBoard findById(Serializable id) {
		return store.findById(id);
	}
	
	public PlayerBoard findPlayerBoard(Player player, DecodingBoard board) {
		return store.findPlayerBoard(player, board);
	}
	
	
	
	
}
