package com.cyeste.games.mastermind.domain.exception;

/**
 * Ejemplo de excepción del dominio la cual informa que le tablero está cerrado, ya sea por estar resuelto o por haber llegado 
 * al tope de intentos
 * @author Christian Yeste Vidal
 *
 */
public class BoardClosedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BoardClosedException(String message) {
		super(message);
	}

}
