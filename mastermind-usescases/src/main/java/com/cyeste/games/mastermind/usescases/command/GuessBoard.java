package com.cyeste.games.mastermind.usescases.command;

import java.util.Arrays;

import com.cyeste.games.mastermind.domain.Pattern;
import com.cyeste.games.mastermind.domain.Pattern.PatternBuilder;
import com.cyeste.games.mastermind.domain.exception.BoardClosedException;
import com.cyeste.games.mastermind.domain.PlayerBoard;
import com.cyeste.games.mastermind.domain.port.DecodingBoardsRepository;
import com.cyeste.games.mastermind.domain.utils.Validations;

/**
 * Componente capturador de casos de uso relacionados con los intentos de resolver un tablero
 * @author Christian Yeste Vidal
 *
 */
public class GuessBoard {

	private final DecodingBoardsRepository store;

	public GuessBoard(DecodingBoardsRepository boardsStore) {
		this.store = boardsStore;
	}

	synchronized public void guess(PlayerBoard playerBoard, String[] guessCode) {
		checkInputs(playerBoard,guessCode);
		PatternBuilder patternBuilder = Pattern.builder();
		Arrays.asList(guessCode).stream().forEach(colorCode -> patternBuilder.addPeg(colorCode));
		Pattern guess = patternBuilder.build();
		playerBoard.getBoard().guess(guess);
		store.store(playerBoard.getBoard());
	}
	
	/**
	 * Validaciones puramente de formato y conformidad. 
	 * REcordemos que DecodingBoard implementa su validación de negoci
	 * @param playerBoard
	 * @param guessCode
	 * @throws BoardClosedException Si el tablero está resuelto o finalizado por número de intentos completado 
	 */
	private void checkInputs(PlayerBoard playerBoard, String[] guessCode) {
		Validations.whenNull(playerBoard).throwIllegalArgumentException("PlayerBoard is required");
		Validations.whenNull(playerBoard.getBoard()).throwIllegalArgumentException("Board is required");
		Validations.whenNull(playerBoard.getPlayer()).throwIllegalArgumentException("Player is required");
		Validations.when(playerBoard.isCoder()).throwIllegalArgumentException("Player can not be code maker");
		Validations.whenEmpty(guessCode).throwIllegalArgumentException("Guess code can not be null nor empty");
	}
}
