package com.cyeste.games.mastermind.application.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract Aspect for Logging access to app components
 * 
 * @author cyeste
 */
public abstract class AbstractLoggerAspect {

	private static final String LOG_MSG_START = "Init";
	private static final String LOG_MSG_END = "End";
	private static final String LOG_PATTERN_SIGNATURE = "{} - {}";

	protected abstract boolean isLoggingArgumentsEnabled();

	protected void doLogging(JoinPoint pjp) {
		Logger logger = getLogger(pjp);
		doAccessToMethodLogging(logger, pjp);

		return;
	}

	protected void doAccessToMethodLogging(Logger logger, JoinPoint pjp) {
		Signature signature = pjp.getSignature();
		logger.info(LOG_PATTERN_SIGNATURE, signature.getName(), LOG_MSG_START);

	}

	protected void doExitToMethodLogging(Logger logger, JoinPoint pjp) {
		Signature signature = pjp.getSignature();
		logger.info(LOG_PATTERN_SIGNATURE, signature.getName(), LOG_MSG_END);

	}

	protected Logger getLogger(JoinPoint pjp) {
		return LoggerFactory.getLogger(pjp.getTarget().getClass());
	}
}
