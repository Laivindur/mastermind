package com.cyeste.games.mastermind.usescases.query;

import java.io.Serializable;
import java.util.Iterator;

import com.cyeste.games.mastermind.domain.DecodingBoard;
import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.PlayerBoard;
import com.cyeste.games.mastermind.domain.port.PlayerBoardsRepository;

public class FindPlayerBoards {

	private final PlayerBoardsRepository playerBoardsRepository;

	public FindPlayerBoards(PlayerBoardsRepository playerBoardsRepository) {
		super();
		this.playerBoardsRepository = playerBoardsRepository;
	}
	
	public Iterator<PlayerBoard> findAllPlayersBoard(Player player) {
		return playerBoardsRepository.findBoards(player).iterator();
	}
	
	public Iterator<PlayerBoard> findAllBoardsPlayers(DecodingBoard board) {
		return playerBoardsRepository.findPlayers(board).iterator();
	}
	
	public PlayerBoard findById(Serializable id) {
		return playerBoardsRepository.findById(id);
	}
	
	public PlayerBoard findPlayerBoard(Player player, DecodingBoard board) {
		return playerBoardsRepository.findPlayerBoard(player, board);
	}
	
	
	
	
}
