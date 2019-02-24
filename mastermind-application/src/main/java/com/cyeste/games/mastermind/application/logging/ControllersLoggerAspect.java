package com.cyeste.games.mastermind.application.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component("controllerLoggerAspect")
public class ControllersLoggerAspect extends AbstractLoggerAspect {

	@Value("${com.cyeste.games.mastermind.aop.loggin.arguments:false}")
	private boolean aopLoggingArgumentsEnabled;

	@Override
	protected boolean isLoggingArgumentsEnabled() {
		return aopLoggingArgumentsEnabled;
	}

	/**
	 * Do track the access to a public controller method of service layer
	 * 
	 * @param pjp
	 *            Pointcut or method accessed
	 */
	@Before("execution(public * com.cyeste.games.mastermind.adapter.rest..*.*(..)) and bean(*Controller*)")
	public void doServiceMethodsAcessLogging(JoinPoint pjp) {
		doLogging(pjp);
	}

	/**
	 * Do track the exit from a public controller method of service layer
	 * 
	 * @param pjp
	 *            Pointcut or method accessed
	 * @param retVal
	 *            value objet returned by the pointcut's execution
	 */
	@AfterReturning(pointcut = "execution(public * com.cyeste.games.mastermind.adapter.rest..*.*(..)) and bean(*Controller*)", returning = "retVal")
	public void doServiceMethodsExitLogging(JoinPoint pjp, Object retVal) {
		Logger logger = getLogger(pjp);
		doExitToMethodLogging(logger, pjp);
		return;
	}

}