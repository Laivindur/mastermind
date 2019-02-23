package com.cyeste.games.mastermind.domain;

import java.io.Serializable;

import com.cyeste.games.mastermind.domain.utils.Validations;

public class PlayerBoard {

	private static final String TO_STRING = "{id: \"%s\", player: %s, board: %s, coder: %s}";
	private final Serializable id;
	private final Player player;
	private final DecodingBoard board;
	private final boolean coder;
	
	PlayerBoard(Serializable id, Player player, DecodingBoard board, boolean coder) {
		Validations.whenNull(player).throwIllegalArgumentException("Player in PlayerBoards is required");
		Validations.whenNull(board).throwIllegalArgumentException("Board in PlayerBoards is required");
		Validations.whenNull(id).throwIllegalArgumentException("PlayerBoard idz is required");

		this.id = id;
		this.player = player;
		this.board = board;
		this.coder = coder;
	}
	public Serializable getId() {
		return id;
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
	
	public static PlayerBoard playerCodeMaker(Serializable id, Player player, DecodingBoard board) {
		return new PlayerBoard(id, player, board, Boolean.TRUE);
	}
	
	public static PlayerBoard playerCodeBreaker(Serializable id, Player player, DecodingBoard board) {
		return new PlayerBoard(id,player, board, Boolean.FALSE);
	}
	
	@Override
	public String toString() {
		return String.format(TO_STRING, id, player,board,String.valueOf(coder));
	}
	
}
