package com.cyeste.games.mastermind.application.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebConfig.class);

	@Bean
	public WebMvcConfigurerAdapter createMVC() {
		WebMvcConfigurer mvcConfig = new WebMvcConfigurer();
		LOGGER.info("{} WebMvc ConfigurerAdapter created!","[Custom]");
		return mvcConfig;
	}
}