package com.cyeste.games.mastermind.domain;

import com.cyeste.games.mastermind.domain.utils.Validations;

/**
 * Componente de tipo Entity que modela la relación entre un jugador y un tablero.
 * La relación se evalua por el estado del flag {@code coder}. 
 * Si {@code code} es <i>true</i> , el jugador es <i>codeMaker</i>
 * Si {@code code} es <i>false</i> , el jugador es <i>codeBreaker</i>
 * @author Christian Yeste Vidal
 *
 */
public class PlayerBoard {

	private static final String TO_STRING = "{id: \"%s\", player: %s, board: %s, coder: %s}";
	private final String id;
	private final Player player;
	private final DecodingBoard board;
	private final boolean coder;
	
	PlayerBoard(String id, Player player, DecodingBoard board, boolean coder) {
		Validations.whenNull(player).throwIllegalArgumentException("Player in PlayerBoards is required");
		Validations.whenNull(board).throwIllegalArgumentException("Board in PlayerBoards is required");
		Validations.whenNull(id).throwIllegalArgumentException("PlayerBoard id is required");

		this.id = id;
		this.player = player;
		this.board = board;
		this.coder = coder;
	}
	public String getId() {
		return id;
	}
	
	public DecodingBoard getBoard(){
		return board;
	}
	
	public boolean isCoder() {
		return coder;
	}
	
	public boolean isBreaker() {
		return !isCoder();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public static PlayerBoard playerCodeMaker(String id, Player player, DecodingBoard board) {
		return new PlayerBoard(id, player, board, Boolean.TRUE);
	}
	
	public static PlayerBoard playerCodeBreaker(String id, Player player, DecodingBoard board) {
		return new PlayerBoard(id,player, board, Boolean.FALSE);
	}
	
	@Override
	public String toString() {
		return String.format(TO_STRING, id, player,board,String.valueOf(coder));
	}
	
}
