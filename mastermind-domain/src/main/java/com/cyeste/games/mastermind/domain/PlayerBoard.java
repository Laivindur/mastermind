package com.cyeste.games.mastermind.domain;

import com.cyeste.games.mastermind.domain.utils.Validations;

public class PlayerBoard {

	private static final String TO_STRING = "{player: %s, board: %s, coder: %s}";
	private final Player player;
	private final DecodingBoard board;
	private final boolean coder;
	
	PlayerBoard(Player player, DecodingBoard board, boolean coder) {
		Validations.whenNull(player).throwIllegalArgumentException("Player in PlayerBoards is required");
		Validations.whenNull(board).throwIllegalArgumentException("Board in PlayerBoards is required");
		this.player = player;
		this.board = board;
		this.coder = coder;
	}
	
	public DecodingBoard getBoard(){
		return board;
	}
	
	public boolean isCoder() {
		return coder;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public static PlayerBoard playerCodeMaker(Player player, DecodingBoard board) {
		return new PlayerBoard(player, board, Boolean.TRUE);
	}
	
	public static PlayerBoard playerCodeBreaker(Player player, DecodingBoard board) {
		return new PlayerBoard(player, board, Boolean.FALSE);
	}
	
	@Override
	public String toString() {
		return String.format(TO_STRING, player,board,String.valueOf(coder));
	}
	
}
