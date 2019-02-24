package com.cyeste.games.mastermind.adapters.store;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractInMemoryStore {

	private final Map<Serializable,Object> datasource;
	
	protected AbstractInMemoryStore() {
		datasource = new HashMap<Serializable, Object>();
	}
	
	protected Map<Serializable, Object> getDatasource() {
		return datasource;
	}
}
