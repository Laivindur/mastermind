package com.cyeste.games.mastermind.application.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.UrlPathHelper;

/**
 * Customización de configuración Spring MVC del Middleware
 * 
 * @author opentrends
 */
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebMvcConfigurer.class);
	private static final String LOG_PATTERN = "[Custom] {} registered";

	// Habilitando los ";" para @MatrixValues
	// http://www.baeldung.com/spring-mvc-matrix-variables
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		urlPathHelper.setRemoveSemicolonContent(false);
		configurer.setUrlPathHelper(urlPathHelper);
	}
	

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {		

		//XXX Not required yet
		LOGGER.info(LOG_PATTERN,"none");
		super.addArgumentResolvers(argumentResolvers);

	}
	
}
