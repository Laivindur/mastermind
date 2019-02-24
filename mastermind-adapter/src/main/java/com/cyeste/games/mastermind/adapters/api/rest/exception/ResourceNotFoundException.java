package com.cyeste.games.mastermind.adapters.api.rest.exception;

public class ResourceNotFoundException extends RuntimeException {

		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Class<?> resourceClass,Object id) {
		super("Resource " + resourceClass.getSimpleName()+" with id " + id+ " not found");
	}
}
