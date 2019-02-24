package com.cyeste.games.mastermind.usescases.query;

import java.util.Iterator;

import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.PlayerBoard;
import com.cyeste.games.mastermind.domain.port.PlayerBoardsRepository;

public class FindPlayerBoards {

	private final PlayerBoardsRepository playerBoardsRepository;

	public FindPlayerBoards(PlayerBoardsRepository playerBoardsRepository) {
		super();
		this.playerBoardsRepository = playerBoardsRepository;
	}
	
	public Iterator<PlayerBoard> findAllBoards(Player player) {
		return playerBoardsRepository.findPlayerBoards(player).iterator();
	}
	
	public Iterator<PlayerBoard> findPlayerBoardsAsCodeMaker(Player player){
		return playerBoardsRepository.findPlayerBoardsAsCodeMaker(player).iterator();
	}
	
	public Iterator<PlayerBoard> findPlayerBoardsAsCodeBreaker(Player player){
		return playerBoardsRepository.findPlayerBoardsAsCodeBreaker(player).iterator();
	}
	
}
