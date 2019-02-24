package com.cyeste.games.mastermind.adapters.generator;

import java.util.UUID;

import com.cyeste.games.mastermind.domain.port.IdGenerator;

/**
 * Generador de cadenas UUID.
 * Estas cadena suelen ser muy fiables como identificadore únicos y universales, lo que es muy apropiado para entornos distribuidos.
 * No obstante así, tiene un coste de computación importante (enla generación, indexación y persitencia ), con lo que no hay que abusar de ellos
 * @author Christian Yeste Vidal
 *
 */
public class UUIDGenerator implements IdGenerator<String> {

	@Override
	public String generate() {
		return UUID.randomUUID().toString();
	}

}
