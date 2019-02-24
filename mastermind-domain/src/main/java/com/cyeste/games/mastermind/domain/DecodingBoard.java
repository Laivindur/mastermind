package com.cyeste.games.mastermind.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import com.cyeste.games.mastermind.domain.exception.BoardClosedException;
import com.cyeste.games.mastermind.domain.utils.Validations;

/**
 * Componente de tipo Entity que modela el tablero de decodificación del juego MasterMind.
 * Como entidad, 
 * @author Christian Yeste Vidal
 *
 */
public class DecodingBoard {

	private static final String TO_STRING = "{id: \"%s\", maxGamesSize : %d, code: %s, games: %s, solved: %s}";
	private final String id;
	private final int maxGamesSize;
	private final Pattern code;
	private final Collection<GuessResult> games;
	private boolean solved;

	private DecodingBoard(String id, int maxGameSize, Pattern code) {
		this.id = id;
		this.code = code;
		this.games = new ArrayList<GuessResult>(maxGameSize);
		this.maxGamesSize = maxGameSize;
		solved = false;
	}
	
	public String getId() {
		return id;
	}

	/**
	 * Ejecuta el intento de descodificar el código del tablero a partir de un código (patrón) de entrada
	 * 
	 * @param guess patrón de entrada
	 * @return GuessResult resultado de la comprovación
	 * @throws BoardClosedException Si el tablero está resuelto o finalizado por número de intentos completado
	 */
	public GuessResult guess(Pattern guess) {
		if (leftGames()) {
			GuessResult result = new GuessResult(code.clone(), code.matchingPegs(guess), code.matchingPegColors(guess));
			solved = result.isExactMatch();
			games.add(result);
			return result;
		}
		throw new BoardClosedException("The board left no more games or is solved.");
	}

	public boolean isSolved() {
		return solved;
	}

	public boolean leftGames() {
		return gamesLeft()  > 0 && !isSolved();
	}

	public Iterator<GuessResult> games() {
		return games.iterator();
	}

	public Pattern getCode() {
		return code;
	}
	
	/**
	 * Método de tipo factoria para instanciar nuevos tableros.
	 * @param id
	 * @param maxGameSize
	 * @param code
	 * @return
	 */
	public static DecodingBoard createBoard(String id, int maxGameSize, Pattern code) {
		Validations.whenNull(code).throwIllegalArgumentException("DecodingBoard's code is required");
		Validations.when(maxGameSize <= 0).throwIllegalArgumentException("DecodingBoard max games size must be greater than 0");
		Validations.whenNull(id).throwIllegalArgumentException("DecodingBoard id is required");
		return new DecodingBoard(id, maxGameSize, code);
	}

	@Override
	public String toString() {
		return String.format(TO_STRING, id.toString(), maxGamesSize, code, Arrays.deepToString(games.toArray()), String.valueOf(solved));
	}

	public int gamesLeft() {
		return maxGamesSize - (int) games.stream().count();
	}
}
