package com.cyeste.games.mastermind.application.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.stereotype.Component;

/**
 * Personalización del pool de conexiones de Tomcat
 * @author Christian Yeste Vidal
 *
 */
@Component
public class ContainerConfig implements EmbeddedServletContainerCustomizer {
 
	private static final Logger LOGGER = LoggerFactory.getLogger(ContainerConfig.class);
	
		
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {		
		TomcatEmbeddedServletContainerFactory.class.cast(container).addConnectorCustomizers(new TomcatConnectorThreadPoolCustomizer());
		LOGGER.info("{} ConnectorCustomerizer [{}] initialized!",TomcatConnectorThreadPoolCustomizer.class.getName());
	}
 
}

