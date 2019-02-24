package com.cyeste.games.mastermind.application.config;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.cyeste.games.mastermind.application.serializer.ZonedDateTimeJacksonDeserializer;
import com.cyeste.games.mastermind.application.serializer.ZonedDateTimeJacksonSerializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class JacksonMapperConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(JacksonMapperConfig.class);

	private ApplicationContext applicationContext;

	@Autowired
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Bean
	public Jackson2ObjectMapperBuilder objctMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.json();
		builder.applicationContext(applicationContext);
		builder.serializationInclusion(JsonInclude.Include.NON_NULL);
		builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
		builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		builder.featuresToDisable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS);

		// Heredado de la autocofiguración de HAL
		builder.featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		builder.dateFormat(new ISO8601DateFormat());
		builder.indentOutput(true);

		List<Module> jacksonModules = new ArrayList<>();

		// Jackson Module para usar constructores con parámetros
		jacksonModules.add(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));
		LOGGER.info("{} added in JacksonMappgerBuilder", ParameterNamesModule.class.getName());
		
		// Jackson module para serializar/deserializar los nuevos tipos de Fecha
		// y hora de Java 8
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(ZonedDateTime.class, new ZonedDateTimeJacksonSerializer());
		javaTimeModule.addDeserializer(ZonedDateTime.class, new ZonedDateTimeJacksonDeserializer());
		jacksonModules.add(javaTimeModule);

		// Jackson module para serializar/deserializar los nuevos tipos de Java
		// 8. Optional por ejemplo
		jacksonModules.add(new Jdk8Module());
		LOGGER.info("{} added in JacksonMappgerBuilder", Jdk8Module.class.getName());
		return builder;
	}

	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(
			Jackson2ObjectMapperBuilder objctMapperBuilder) {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter(
				objctMapperBuilder.build());
		LOGGER.info("{} mappingJackson2HttpMessageConverter created!","[Custom]");
		return jsonConverter;
	}
}
