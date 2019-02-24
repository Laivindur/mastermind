package com.cyeste.games.mastermind.adapters.api.rest.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cyeste.games.mastermind.adapters.api.rest.exception.ResourceNotFoundException;

@RestControllerAdvice(basePackages = "com.cyeste.games.mastermind.adapters.api.rest")
public class RestControllerErrorHandler {

	/**
	 * Runtime and unexpected error handling
	 * @param request
	 * @param response
	 * @param e
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ResponseEntity<ErrorResource> defaultExceptionHandler(Throwable ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResource("Ups! something went wrong up here",ex));

	}
	
	/**
	 * Default invalid request parameters and business validations error handler
	 * @param request
	 * @param response
	 * @param e
	 * @return
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ResponseEntity<ErrorResource> badRequest(Throwable ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResource(ex.getMessage(),ex));

	}

	/**
	 * REsource not found error handler
	 * @param request
	 * @param response
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ResponseEntity<ErrorResource> notFound(Exception ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResource(ex.getMessage(),ex));

	}
	/**
	 * Metodo HTTP no soportado
	 * @param request
	 * @param response
	 * @param e
	 * @return
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
	public ResponseEntity<ErrorResource> methodNotAllowed(Exception ex) {
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(new ErrorResource(ex.getMessage(),ex));

	}
	
}
