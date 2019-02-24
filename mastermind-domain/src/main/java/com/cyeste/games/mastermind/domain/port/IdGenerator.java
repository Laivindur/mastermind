package com.cyeste.games.mastermind.domain.port;

/**
 * Interfaz o contrato del coponente del servicio candidato a ser generador de Identificadores de entidades.
 * Los componentes que implementen esta interfaz, deberían generar identificadores únicos y universales
 * @author Christian Yeste Vidal
 * 
 */
public interface IdGenerator {

	String generate();
}
