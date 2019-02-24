package com.cyeste.games.mastermind.usescases.query;

import java.util.Iterator;

import com.cyeste.games.mastermind.domain.DecodingBoard;
import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.PlayerBoard;
import com.cyeste.games.mastermind.domain.port.PlayerBoardsRepository;

/**
 * Componente capturador de casos de uso relacionados con  la búsqueda de relaciones jugadores-tableros
 * @author Christian Yeste Vidal
 *
 */
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
	
	public PlayerBoard findById(String id) {
		return store.findById(id);
	}
	
	public PlayerBoard findPlayerBoard(Player player, DecodingBoard board) {
		return store.findPlayerBoard(player, board);
	}
	
	
	
	
}
