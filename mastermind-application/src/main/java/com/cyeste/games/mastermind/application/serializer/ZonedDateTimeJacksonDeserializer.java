package com.cyeste.games.mastermind.application.serializer;


import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class ZonedDateTimeJacksonDeserializer extends JsonDeserializer<ZonedDateTime> {

	
	@Override
	public ZonedDateTime deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException {
		return ZonedDateTime.parse(p.getValueAsString(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
	}

}
