package com.cyeste.games.mastermind.domain.port;

import java.io.Serializable;

/**
 * 
 * @author Christian Yeste Vidal
 *
 * @param <T>
 */
public interface IdGenerator<T extends Serializable> {

	T generate();
}
