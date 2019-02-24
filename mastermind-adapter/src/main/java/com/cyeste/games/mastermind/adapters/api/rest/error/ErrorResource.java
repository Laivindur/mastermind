package com.cyeste.games.mastermind.adapters.api.rest.error;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class ErrorResource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String code;
	private final Object[] arguments;
	private final Throwable cause;

	public ErrorResource(String code, Throwable cause) {
		this.code = code;
		this.cause = cause;
		this.arguments = null;
	}

	public ErrorResource(String code, Object[] arguments, Throwable cause) {
		this.code = code;
		this.arguments = arguments;
		this.cause = cause;
	}

	public String getCode() {
		return code;
	}

	public Object[] getArguments() {
		return arguments;
	}

	public String getCauseMessage() {
		if (cause != null) {
			return cause.getMessage();
		}
		return null;
	}

	public String getCause() {
		if (cause != null) {
			return cause.getClass().getName();
		}
		return null;
	}

	public Serializable getThreadId() {
		return Thread.currentThread().getId();
	}

	public ZonedDateTimeResource getDate() {
		return new ZonedDateTimeResource(ZonedDateTime.now());
	}
}