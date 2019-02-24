package com.cyeste.games.mastermind.application.config;

import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;

/**
 * Personalización de los parámetros de configuración del servidor embebido en
 * el que se ejecuta la app. En el caso de Culturapp-rs, Tomcat.
 * 
 * Se implementa este componente, para establecer el punto de configuración, en caso 
 * de ser necesaria una parametrización a medida.
 * 
 * A priori, los valores establecidos para las propiedades del ThreadPool del conector, son 
 * las de por defecto.
 * @author cyeste
 * 
 *
 */
public class TomcatConnectorThreadPoolCustomizer implements TomcatConnectorCustomizer {

	private static final Logger LOGGER = LoggerFactory.getLogger(TomcatConnectorThreadPoolCustomizer.class);
	private static final Integer MAX_THREADS = 250;
	private static final Integer MAX_CONNECTIONS = 10000;
	private static final Integer ACCEPT_COUNT = 125;
	
	/*
	 * Para más información al respecto de las properties del conector de Catalina Tomcat, ver 
	 * http://tomcat.apache.org/tomcat-8.0-doc/config/http.html
	 * http://tomcat.apache.org/tomcat-7.0-doc/config/http.html
	 * */
	@Override
	public void customize(Connector connector) {
		connector.setProperty("maxThreads", String.valueOf(MAX_THREADS));
		connector.setProperty("maxConnections", String.valueOf(MAX_CONNECTIONS));
		connector.setProperty("acceptCount", String.valueOf(ACCEPT_COUNT));
		
		LOGGER.info("Tomcat's connector thread pool: {}. AcceptCount: {}, MaxThreads: {}, MaxConnctions: {}", 
				connector,
				connector.getProperty("acceptCount"),
				connector.getProperty("maxThreads"), 
				connector.getProperty("maxConnections"));
	}

}

