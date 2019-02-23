package com.cyeste.games.mastermind.domain.exception;

/**
 * 
 * @author Christian Yeste Vidal
 *
 */
public class InvalidOperationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidOperationException(String message) {
		super(message);
	}

}
