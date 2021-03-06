package com.cyeste.games.mastermind.usescases.command;

import java.util.Collection;
import java.util.Optional;

import com.cyeste.games.mastermind.domain.DecodingBoard;
import com.cyeste.games.mastermind.domain.Player;
import com.cyeste.games.mastermind.domain.PlayerBoard;
import com.cyeste.games.mastermind.domain.port.IdGenerator;
import com.cyeste.games.mastermind.domain.port.PlayerBoardsRepository;
import com.cyeste.games.mastermind.domain.utils.Validations;

/**
 * Componente capturador de casos de uso relacionados con la creación de vínculos entre jugador y tablero.
 * Las dós únicas relaciones que puede tener un jugador con un tablero son como : codeMaker (generador del tablero) o 
 * codeBreaker (jugador que intenta descifrar el tablero)
 * @author Christian Yeste Vidal
 *
 */
public class JoinBoard {

	//XXX : Esto podría ser parametrizable por aplicación.
	private static final int DEFAULT_MAX_PLAYERS_PER_BOARD = 2;
	
	private final PlayerBoardsRepository store;
	private final IdGenerator idGenerator;

	public JoinBoard(PlayerBoardsRepository repository, IdGenerator idGenerator) {
		this.store = repository;
		this.idGenerator = idGenerator;
	}

	public PlayerBoard joinAsCodeMaker(Player codeMaker, DecodingBoard board) {
		
		checkInputs(codeMaker, board);
		
		//Miramos si ya estamos en el juego
		Optional<PlayerBoard> alreadyJoinedBoard = Optional.ofNullable(store.findPlayerBoard(codeMaker, board));
		//Y si somos los makers
		if(alreadyJoinedBoard.isPresent() && alreadyJoinedBoard.get().isCoder()) {
			return alreadyJoinedBoard.get();
		}
		
		//Estamos en el juego, pero no somos makers, sino breakers. Esta operacion no puede seguir
		Validations.when(alreadyJoinedBoard.isPresent() && alreadyJoinedBoard.get().isBreaker())
		.throwIllegalArgumentException("The board " + board.getId() + " already has a code maker");
		
			
		PlayerBoard playerBoard = PlayerBoard.playerCodeMaker(idGenerator.generate(), codeMaker, board);
		store.store(playerBoard);
		return playerBoard;
	}

	public PlayerBoard joinAsCodeBreaker(Player codeBreaker, DecodingBoard board) {
		
		checkInputs(codeBreaker, board);
		
		//Miramos si ya estamos en el juego
		Optional<PlayerBoard> alreadyJoinedBoard = Optional.ofNullable(store.findPlayerBoard(codeBreaker, board));
		
		//y si somos code breakers
		if(alreadyJoinedBoard.isPresent() && alreadyJoinedBoard.get().isBreaker()) {
			return alreadyJoinedBoard.get();
		}
		
		//Si existe y no somos los breakers... es que somos los makers!
		Validations.when(alreadyJoinedBoard.isPresent() && alreadyJoinedBoard.get().isCoder())
		.throwIllegalArgumentException("Player "+codeBreaker.getId()+" can not join board " + board.getId()+" because it's the code maker");
		
		//Ahora si miramos si hay plazas
		Collection<PlayerBoard> players = store.findPlayers(board);
		Validations.when(players.size() == DEFAULT_MAX_PLAYERS_PER_BOARD).throwIllegalArgumentException("No more players are allowed for board " + board.getId());
		
		
		PlayerBoard playerBoard = PlayerBoard.playerCodeBreaker(idGenerator.generate(), codeBreaker, board);
		store.store(playerBoard);
		return playerBoard;
	}
	
	private void checkInputs(Player player, DecodingBoard board) {
		Validations.whenNull(player).throwIllegalArgumentException("Code breaker is required");
		Validations.whenNull(board).throwIllegalArgumentException("Board is required");
	}
}
